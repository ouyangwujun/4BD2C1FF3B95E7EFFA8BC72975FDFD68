package com.object.test.api.open.entity.response;

import com.sun.org.glassfish.gmbal.Description;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by PUZE81 on 2017/3/1.
 */
@Description("包装的响应对象")
@XmlRootElement(name = "TestResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestResponse implements Serializable{

    private static final long serialVersionUID = -1263417941788097412L;
    @Description("Code属性")
    @XmlElement(name="Code")
    private Long code;

    @Description("Name属性")
    @XmlElement(name="Name")
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
        return "TestResponse{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
