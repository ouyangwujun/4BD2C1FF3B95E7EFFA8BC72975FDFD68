package com.object.test.api.open;

import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;
import com.wordnik.swagger.annotations.*;
import org.springframework.http.MediaType;

/**
 * Created by PUZE81 on 2017/2/27.
 */
@Api(value = "IDubboTest")
public interface IDubboTest {
    @ApiOperation(
            value = "testMathedPost测试方法" ,
            notes = "Get测试方法",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testRequest", value = "测试实体类", required = true, dataType = "TestRequest")
    })
    @ApiResponses({
            @ApiResponse(code = 404, message = "找不到页面", response = TestRequest.class)
    })
    TestResponse testMathedPost(TestRequest testRequest);

    @ApiOperation(value = "testMathedPost测试方法", notes = "Post测试方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testRequest", value = "测试实体类", required = true, dataType = "TestRequest")
    })
    TestResponse testMathedGet(@ApiParam(value="token", required = true) String testRequest);
}
