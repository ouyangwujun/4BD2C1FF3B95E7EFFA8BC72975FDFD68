package com.object.test.api.open.entity;

import com.object.entity.BaseErrorCode;

/**
 * validate
 * Created by PUZE81 on 2017/7/3.
 */
public class ValidateErrorCode extends BaseErrorCode{

    public static class Code{

        public final static int TEST_ERR = 3000001;

    }

    public static class Desc{
        public final static String TEST_ERR = "测试接口调用异常";

    }
}
