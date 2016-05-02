package com.weixin.platform.domain.state;


/**
 * Created by ruanzf on 2015/4/22.
 */
public enum ProductStateEnum {
    PUBLISH(1, "PUBLISH"),
    SOLDOUT(2, "SOLDOUT"),
    OK(3, "OK");

    private int code;
    private String name;

    ProductStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
