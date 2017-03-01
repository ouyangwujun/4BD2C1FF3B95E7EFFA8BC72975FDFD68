package com.object.test.api.open.entity.request;

import com.sun.org.glassfish.gmbal.Description;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by PUZE81 on 2017/3/1.
 */
@Description("包装的请求对象")
@XmlRootElement(name = "TestRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestRequest implements Serializable{

    private static final long serialVersionUID = 582058496291742891L;
    @Description("Code属性")
    @XmlElement(name="Code")
    private Long code;

    @Description("Name属性")
    @XmlElement(name="Name")
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
        return "TestRequest{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

}
