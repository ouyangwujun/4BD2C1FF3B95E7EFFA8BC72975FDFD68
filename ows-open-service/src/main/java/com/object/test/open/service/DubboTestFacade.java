package com.object.test.open.service;

import com.alibaba.dubbo.integration.security.model.SecurityType;
import com.object.test.api.open.IDubboTest;
import com.object.test.api.open.entity.BaseResponseEntity;
import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;

/**
 * Reset接口开放
 * Created by ouyang on 2017/3/1.
 * Consumes:方法可以接受的 MIME 类型
 * Produces:方法可以返回的 MIME 类型
 */
public class DubboTestFacade implements IDubboTest{

    private static final Logger logger  = LoggerFactory.getLogger(DubboTestFacade.class);

    public BaseResponseEntity<TestResponse> testMathedPost(TestRequest testRequest) {
        BaseResponseEntity<TestResponse> testResponse = new BaseResponseEntity<TestResponse>();
        BeanUtils.copyProperties(testRequest, testResponse);
        logger.info("open testMathedPost Return :{}",testRequest.toString());
        return testResponse;
    }

    public BaseResponseEntity<TestResponse> testMathedGetCookie(@CookieParam(SecurityType.APP_KEY) String appKey, @CookieParam(SecurityType.TOKEN) String token, String name) {
        BaseResponseEntity<TestResponse> testResponse = BaseResponseEntity.getInstance(new TestResponse());
        testResponse.getData().setCode(10000L);
        testResponse.getData().setName(name);
        logger.info("open testMathedGet Return :{}",testResponse.toString());
        return testResponse;
    }

    @Override
    public BaseResponseEntity<TestResponse> testMathedGetHeader(@HeaderParam(SecurityType.APP_KEY) String appKey, @HeaderParam(SecurityType.TOKEN) String token, String name) {
        BaseResponseEntity<TestResponse> testResponse = BaseResponseEntity.getInstance(new TestResponse());
        testResponse.getData().setCode(10000L);
        testResponse.getData().setName(name);
        logger.info("open testMathedGetV2 Return :{}",testResponse.toString());
        return testResponse;
    }
}
