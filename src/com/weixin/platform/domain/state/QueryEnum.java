package com.weixin.platform.domain.state;


import com.weixin.platform.wxtools.Tools;

/**
 * Created by ruanzf on 2015/4/22.
 */
public enum QueryEnum {
    NONE("NONE", Tools.NONE),
    ID("ID", "编号"),
    QUERY("QUERY", "买"),
    MINE("MINE", "我的"),
    ;

    private String code;
    private String name;

    QueryEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static QueryEnum getByName(String content) {
        for(QueryEnum queryEnum : QueryEnum.values()) {
            if(queryEnum.getName().equals(content)) {
                return queryEnum;
            }
        }
        String code = ID.getName() + Tools.division;
        if(content.startsWith(code)) {
            return ID;
        }
        return NONE;
    }

    public static boolean me(String content) {
        for(QueryEnum flow : QueryEnum.values()) {
            if(flow.getName().equals(content)) {
                return true;
            }
        }
        String code = ID.getName() + Tools.division;
        if(content.startsWith(code)) {
            return true;
        }
        return false;
    }
}
