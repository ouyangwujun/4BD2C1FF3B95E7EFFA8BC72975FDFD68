package com.object.test.api.open;

import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PUZE81 on 2017/2/27.
 */
@Api(value = "IDubboTest")
@RestController
@RequestMapping(value="/services")
public interface IDubboTest {

    @ApiOperation(value = "testMathedPost测试方法" ,notes = "Get测试方法")
    @ApiImplicitParam(name = "testRequest", value = "测试实体类", required = true, dataType = "TestRequest")
    @RequestMapping(value = {"testMathedPost"}, method = RequestMethod.GET)
    TestResponse testMathedPost(TestRequest testRequest);

    @ApiOperation(value = "testMathedPost测试方法", notes = "Post测试方法")
    @RequestMapping(value = {"testMathedPost"}, method = RequestMethod.POST)
    TestResponse testMathedGet(@ApiParam(value="token", required = true) String testRequest);
}
