package com.object.swagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by PUZE81 on 2017/3/13.
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class RestApiConfig extends WebMvcConfigurationSupport{

    private static final Logger logger = LoggerFactory.getLogger(RestApiConfig.class.getName());

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                .apis(Predicates.and(RequestHandlerSelectors.withClassAnnotation(RestController.class)))
                .build();
    }
    /**
     *apis V2
     * @return
     */
    private Predicate<RequestHandler> transformApis() {
        return new Predicate<RequestHandler>() {
            public boolean apply(RequestHandler input) {
                Class<?>  zclass = input.getHandlerMethod().getMethod().getDeclaringClass();
                //RestController
                RestController apis = zclass.getAnnotation(RestController.class);
                if(apis!=null){
                    return true;
                }
                return false;
            }
        };
    }
    /**
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Ouyang", "www.baidu.com", "619435294@qq.com");
        return new ApiInfo("前台API接口",//大标题 title
                "前台API接口",//小标题
                "0.0.1",//版本
                "www.baidu.com",//termsOfServiceUrl
                contact,//作者
                "前台",//链接显示文字
                "www.baidu.com"//网站链接
        );
    }
}
