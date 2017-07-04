package com.object.entity;

import java.io.Serializable;

/**
 * Created by ouyang on 2017/7/3.
 */
public class BaseErrorCode implements Serializable {

    public static final long serialVersionUID = 2403306874401029303L;

    public static class Code{

        public final static int  SUCCEED = 0;

        public final static int CUSTOM_BUSINESS_ERR = 1000000;

        public final static int CUSTOM_APP_ERR = 2000000;

        public final static int CUSTOM_VALIDATE_ERR = 3000000;

    }

    public static class Desc{

        public final static String  SUCCEED = "成功";

        public final static String CUSTOM_BUSINESS_ERR = "业务异常";

        public final static String CUSTOM_APP_ERR = "应用异常";

        public final static String CUSTOM_VALIDATE_ERR = "校验异常";

    }
}
