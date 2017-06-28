package com.object.test.api.open;

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
    @Path("testMathedGet")
    @ApiOperation(
            value = "testMathedPost测试方法",
            notes = "GET测试方法",
            response = TestResponse.class
    )
    TestResponse testMathedGet(@QueryParam("n") String testRequest);
}
