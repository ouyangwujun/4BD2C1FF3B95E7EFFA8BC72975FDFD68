package com.object.swagger.controller;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.JarFile;

/**
 * 读取接口中的文档信息
 *      原始的swagger2只能读取实现类中的文档信息
 * Created by PUZE81 on 2017/3/16.
 */
@Controller
@RequestMapping(value = "/v3")
public class Swagger3Controller {

    private static final Logger logger = LoggerFactory.getLogger(Swagger3Controller.class.getName());

    public static final String DEFAULT_URL = "/api-docs";

    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    @Value("${springfox.documentation.swagger.v2.host:DEFAULT}")
    private String hostNameOverride;
    @Autowired
    private DocumentationCache documentationCache;
    @Autowired
    private ServiceModelToSwagger2Mapper mapper;
    @Autowired
    private JsonSerializer jsonSerializer;

    @ApiIgnore
    @ResponseBody
    @RequestMapping(value = DEFAULT_URL,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Json> getDocumentation(
                @RequestParam(value = "group",required = false) String swaggerGroup,
                HttpServletRequest servletRequest) {
        String groupName = (String)Optional.fromNullable(swaggerGroup).or("default");
        Documentation documentation = this.documentationCache.documentationByGroup(groupName);
        if(documentation == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            Swagger swagger = getSwagger(documentation);
/*            if(Strings.isNullOrEmpty(swagger.getHost())) {
                swagger.host(this.hostName(servletRequest));
            }*/
            return new ResponseEntity(this.jsonSerializer.toJson(swagger), HttpStatus.OK);
        }
    }

    private Swagger  getSwagger(Documentation documentation){
        Swagger swagger = this.mapper.mapDocumentation(documentation);
        logger.info("Paths:"+ swagger.getPaths());
        ClassLoader loader = getClass().getClassLoader();
        File apiJarDirectory = new File(loader.getResource("/api").getFile());
        if (apiJarDirectory.exists() && apiJarDirectory.isDirectory()) {
            File[] files = apiJarDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith(".jar");
                }
            });
            if(files!=null){
                for (File file : files) {
                    URL url1 = null;
                    URLClassLoader urlClassLoader = null;
                    try {
                        url1 = new URL("file:"+file.getPath());
                        urlClassLoader = new URLClassLoader(new URL[] { url1 },
                                            Thread.currentThread().getContextClassLoader());
                        JarFile jfile = new JarFile(file);
                        //取得MANIFEST.MF文件中的rest服务接口
                        if ("rest".equals(jfile.getManifest().getMainAttributes().getValue("Api-Dependency-Type"))) {
                            String ns = jfile.getManifest().getMainAttributes().getValue("Api-Export");
                            String[] classNames = ns.split(" ");
                            for (String name : classNames) {
                                if (!Strings.isNullOrEmpty(name)) {
                                    Class<?> clazz = urlClassLoader.loadClass(name.trim());
                                    boolean isApiAnnotation = clazz.isAnnotationPresent(Api.class);
                                    if(isApiAnnotation){
                                        logger.info("isApiAnnotation:" +isApiAnnotation);
                                    }
                                }
                            }
                        }
                    }catch (Throwable t){
                        logger.error("Loader JarFile Error:" +t.getMessage(), t);
                    }finally {
                        try {
                            if(urlClassLoader != null) urlClassLoader.close();
                        } catch (IOException e) {
                            logger.error("Close Error:" +e.getMessage(), e.fillInStackTrace());
                        }
                    }
                }
            }
        }
        swagger.setPaths(new HashMap<String, Path>());
        return swagger;
    }
}
