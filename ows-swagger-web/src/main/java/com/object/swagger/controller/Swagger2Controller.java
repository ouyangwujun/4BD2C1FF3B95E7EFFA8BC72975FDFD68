package com.object.swagger.controller;

import com.google.common.base.Optional;
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

/**
 * 读取接口中的文档信息
 *      原始的swagger2只能读取实现类中的文档信息
 * Created by PUZE81 on 2017/3/16.
 */
@Controller
@RequestMapping(value = "/v2")
public class Swagger2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Swagger2Controller.class.getName());

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
            Swagger swagger = this.mapper.mapDocumentation(documentation);
            return new ResponseEntity(this.jsonSerializer.toJson(swagger), HttpStatus.OK);
        }
    }
}
