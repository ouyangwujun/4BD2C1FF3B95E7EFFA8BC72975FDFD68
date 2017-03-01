package com.object.test.api.disopen.enums;

/**
 * Created by PUZE81 on 2017/2/27.
 */
public enum ServiceExceptionEnum {

    ADDUSER("00000000", "未知错误"),
    CHANGEBIND("10000000", "认证错误")
    ;

    private String exceptionCode;
    private String exceptionMsg;

    private ServiceExceptionEnum( String exceptionCode, String exceptionMsg) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }
}
