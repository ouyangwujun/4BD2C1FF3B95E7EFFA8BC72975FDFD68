package com.object.swagger.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
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

//    @Bean
//    public Docket createRestApiV1() {
//        /**
//         * selector
//         */
//        Predicate<RequestHandler> selector = new Predicate<RequestHandler>() {
//            @Override
//            public boolean apply(RequestHandler input) {
//                Class<?> declaringClass = input.declaringClass();
//                if (declaringClass == Controller.class) //排除
//                    return false;
//                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
//                    return true;
//                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
//                    return true;
//                return false;
//            }
//        };
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("V1")
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//                .select()
//                .paths(PathSelectors.any())
//                .apis(selector)
//                .build();
//    }

    @Bean
    public Docket createRestApiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                //下面这句代码是只生成被ApiOperation这个注解注解过的api接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    /**
     * createRestApi apiInfo
     * @return ApiInfo
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
