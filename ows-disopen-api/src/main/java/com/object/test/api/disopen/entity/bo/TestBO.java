package com.object.test.api.disopen.entity.bo;

import java.io.Serializable;

/**
 * Created by PUZE81 on 2017/3/1.
 */
public class TestBO implements Serializable{

    private static final long serialVersionUID = -1287279736467522881L;

    private Long code;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TestBo{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
