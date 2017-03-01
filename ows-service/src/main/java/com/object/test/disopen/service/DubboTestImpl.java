package com.object.test.disopen.service;

import com.object.test.api.disopen.IDubboTest;
import com.object.test.api.disopen.entity.bo.TestBO;
import com.object.test.api.disopen.entity.dto.TestDTO;
import com.object.test.api.disopen.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Created by PUZE81 on 2017/2/28.
 */
public class DubboTestImpl implements IDubboTest {

    private static final Logger logger  = LoggerFactory.getLogger(DubboTestImpl.class);

    @Override
    public void testMathed() throws ServiceException {
        logger.info("testMathed:{}","test mathed.");
    }

    @Override
    public TestBO testMathed(TestDTO testDTO) throws ServiceException {
        TestBO testBO = new TestBO();
        BeanUtils.copyProperties(testDTO, testBO);
        logger.info("testMathed Return TestBO:{}",testBO.toString());
        return testBO;
    }
}
