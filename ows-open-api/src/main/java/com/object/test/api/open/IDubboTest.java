package com.object.test.api.open;

import com.alibaba.dubbo.integration.security.model.SecurityType;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;
import io.swagger.annotations.*;

import javax.ws.rs.*;

/**
 * Created by PUZE81 on 2017/2/27.
 */
@Path("test")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Api(value = "测试管理")
public interface IDubboTest {

    @POST
    @Path("testMathedPost")
    @ApiOperation(
            value = "testMathedPost测试方法" ,
            notes = "POST测试方法",
            response = TestResponse.class
    )
    TestResponse testMathedPost(@BeanParam TestRequest testRequest);

    @GET
    @Path("testMathedGetCookie")
    @ApiOperation(
            value = "testMathedGetCookie测试方法",
            notes = "GET测试方法",
            response = TestResponse.class
    )
    TestResponse testMathedGetCookie(
            @CookieParam(SecurityType.APP_KEY) String appKey,
            @CookieParam(SecurityType.TOKEN) String token,
            @QueryParam("n") String name);

    @GET
    @Path("testMathedGetHeader")
    @ApiOperation(
            value = "testMathedGetHeader测试方法",
            notes = "GET测试方法",
            response = TestResponse.class
    )
    TestResponse testMathedGetHeader(
            @HeaderParam(SecurityType.APP_KEY) String appKey,
            @HeaderParam(SecurityType.TOKEN) String token,
            @QueryParam("n") String name);
}
