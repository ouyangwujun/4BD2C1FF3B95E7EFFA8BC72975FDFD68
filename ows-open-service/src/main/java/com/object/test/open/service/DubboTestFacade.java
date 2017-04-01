package com.object.test.open.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.object.test.api.open.IDubboTest;
import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Reset接口开放
 * Created by ouyang on 2017/3/1.
 * Consumes:方法可以接受的 MIME 类型
 * Produces:方法可以返回的 MIME 类型
 */
public class DubboTestFacade implements IDubboTest{

    private static final Logger logger  = LoggerFactory.getLogger(DubboTestFacade.class);

    public TestResponse testMathedPost(TestRequest testRequest) {
        TestResponse testResponse = new TestResponse();
        BeanUtils.copyProperties(testRequest, testResponse);
        logger.info("open testMathedPost Return :{}",testRequest.toString());
        return testResponse;
    }

    public TestResponse testMathedGet(String name) {
        TestResponse testResponse = new TestResponse();
        testResponse.setCode(1000000L);
        testResponse.setName(name);
        logger.info("open testMathedGet Return :{}",testResponse.toString());
        return testResponse;
    }
}
