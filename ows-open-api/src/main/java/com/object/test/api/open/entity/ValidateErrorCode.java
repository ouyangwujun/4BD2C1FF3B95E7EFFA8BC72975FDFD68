package com.object.test.api.open.entity;

import com.object.entity.BaseErrorCode;

/**
 * validate
 * Created by PUZE81 on 2017/7/3.
 */
public class ValidateErrorCode extends BaseErrorCode{

    protected ValidateErrorCode(String desc, int code) {
        super(desc, code);
    }

    private final static int _C_TEST_ERR = 3000001;
    public final static BaseErrorCode TEST_ERR = new BaseErrorCode("测试接口调用异常", _C_TEST_ERR);
}
