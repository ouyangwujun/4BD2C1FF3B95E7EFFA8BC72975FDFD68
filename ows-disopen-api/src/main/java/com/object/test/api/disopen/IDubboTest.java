package com.object.test.api.disopen;

import com.object.test.api.disopen.entity.bo.TestBO;
import com.object.test.api.disopen.entity.dto.TestDTO;
import com.object.test.api.disopen.exception.ServiceException;

/**
 * Created by PUZE81 on 2017/2/27.
 */
public interface IDubboTest {

    void testMathed()throws ServiceException;

    TestBO testMathed(TestDTO testDTO)throws ServiceException;
}
