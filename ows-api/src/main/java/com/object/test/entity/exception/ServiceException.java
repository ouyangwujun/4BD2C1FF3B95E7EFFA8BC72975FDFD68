package com.object.test.entity.exception;

import com.object.test.entity.enums.ServiceExceptionEnum;

/**
 * Created by PUZE81 on 2017/2/27.
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5478559871753499374L;

    private ServiceExceptionEnum errorCode;
    private String errorMessage;

    public ServiceException() {
    }

    public ServiceException(ServiceExceptionEnum errorCode) {
        super((String)null);
        this.errorCode = errorCode;
    }
}
