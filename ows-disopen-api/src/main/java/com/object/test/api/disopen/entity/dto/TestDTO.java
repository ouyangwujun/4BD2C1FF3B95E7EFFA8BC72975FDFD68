package com.object.test.api.disopen.entity.dto;

import java.io.Serializable;

/**
 * Created by PUZE81 on 2017/3/1.
 */
public class TestDTO implements Serializable {

    private static final long serialVersionUID = 7444172559766782648L;

    private Long code;

    private String name;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
