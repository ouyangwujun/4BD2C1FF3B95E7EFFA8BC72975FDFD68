package com.object.test.api.open.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Base Entity Response
 * Created by ouyang on 2017/7/3.
 */
@ApiModel(value = "包装的响应对象" ,description = "包装的响应对象描述")
public class BaseResponseEntity<T> implements Serializable{

    public static <T> BaseResponseEntity<T> getInstance(T data){
        BaseResponseEntity<T> responseEntity = new BaseResponseEntity<T>();
        responseEntity.setData(data);
        return responseEntity;
    }
    /**
     * code
     */
    @ApiModelProperty(value = "返回码", required = true)
    private int code;
    /**
     * message
     */
    @ApiModelProperty(value = "返回消息", required = true)
    private String message;
    /**
     * data entity
     */
    @ApiModelProperty(value = "返回的实体", required = true)
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
