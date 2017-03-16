package com.object.swagger.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.common.base.Optional;
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
import java.util.HashMap;

/**
 * 读取接口中的文档信息
 *      原始的swagger2只能读取实现类中的文档信息
 * Created by PUZE81 on 2017/3/16.
 */
@Controller
@RequestMapping(value = "/v3")
public class Swagger3Controller {

    private static final Logger logger = LoggerFactory.getLogger(Swagger3Controller.class.getName());

    public static final String DEFAULT_URL = "/v3/api-docs";

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
    @RequestMapping(value = "/api-docs",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
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
        File apiJarDirectory = new File(ClassLoader.getSystemClassLoader().getResource("/api").getFile());


        swagger.setPaths(new HashMap<String, Path>());
        return swagger;
    }
}
