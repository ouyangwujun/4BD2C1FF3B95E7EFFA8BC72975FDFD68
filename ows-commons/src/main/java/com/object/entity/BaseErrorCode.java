package com.object.entity;

import java.io.Serializable;

/**
 * Created by ouyang on 2017/7/3.
 */
public class BaseErrorCode implements Serializable {

    private static final long serialVersionUID = 2403306874401029303L;

    private final static int _C_CUSTOM_BUSINESS_ERR = 1000000;
    public final static BaseErrorCode CUSTOM_BUSINESS_ERR = new BaseErrorCode("业务异常", _C_CUSTOM_BUSINESS_ERR);

    private final static int _C_CUSTOM_APP_ERR = 2000000;
    public final static BaseErrorCode CUSTOM_APP_ERR = new BaseErrorCode("应用异常", _C_CUSTOM_APP_ERR);

    private final static int _C_CUSTOM_VALIDATE_ERR = 3000000;
    public final static BaseErrorCode CUSTOM_VALIDATE_ERR = new BaseErrorCode("校验异常", _C_CUSTOM_VALIDATE_ERR);


    private static int code = -1;

    private static String desc = "";

    public BaseErrorCode(String desc, int code) {
        BaseErrorCode.code = code;
        BaseErrorCode.desc = desc;
    }

    public static String getDesc() {
        return desc;
    }

    public static int getCode() {
        return code;
    }
}
