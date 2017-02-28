package com.object.test.service;

import com.object.test.api.IDubboTest;
import com.object.test.entity.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PUZE81 on 2017/2/28.
 */
public class DubboTestImpl implements IDubboTest{

    private static final Logger logger  = LoggerFactory.getLogger(DubboTestImpl.class);

    @Override
    public void testMathed() throws ServiceException {
        logger.info("testMathed:{}","测试方法.");
    }
}
