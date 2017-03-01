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
 * Created by PUZE81 on 2017/3/1.
 */
@Path("dubboTestFacade")
public class DubboTestFacade implements IDubboTest{

    private static final Logger logger  = LoggerFactory.getLogger(DubboTestFacade.class);

    @GET
    @Path("searchTest")
    @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
    public TestResponse testMathed(@PathParam("queryTest") TestRequest testRequest) {
        TestResponse testResponse = new TestResponse();
        BeanUtils.copyProperties(testRequest, testResponse);
        logger.info("open testMathed Return TestBO:{}",testRequest.toString());
        return testResponse;
    }

    @POST
    @Path("pullTest")
    @Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
    public void testMathed() {
        logger.info("open testMathed:{}","test mathed.");
    }
}
