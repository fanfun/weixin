package com.weixin.platform.domain.state;


/**
 * Created by ruanzf on 2015/4/22.
 */
public enum ProductEnum {

    SELL(1, "SELL", "卖"),
    ASK_BUY(10, "ASK_BUY", "求购")
    ;

    private int id;
    private String code;
    private String name;

    ProductEnum(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return id + "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ProductEnum getById(int id) {
        if(ASK_BUY.getId() == id) {
            return ASK_BUY;
        }else {
            return SELL;
        }
    }

    public static ProductEnum getByCode(String code) {
        if(ASK_BUY.getCode().equals(code)) {
            return ASK_BUY;
        }else {
            return SELL;
        }
    }

}
