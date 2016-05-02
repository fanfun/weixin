package com.weixin.platform.domain.state;

import com.weixin.platform.wxtools.Tools;

/**
 * Created by ruanzf on 2015/4/22.
 */
public enum SellEnum {
    SELL("SELL", "卖"),
    ASK_BUY("ASK_BUY", "求")
    ;

    private String code;
    private String name;

    SellEnum(String code, String name) {
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

    public static boolean me(String content) {
        if(!Tools.validate(content)) {
            return false;
        }
        int start = content.indexOf(Tools.division);
        String name = content.substring(0, start);
        for(SellEnum flow : SellEnum.values()) {
            if(flow.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static SellEnum getTrade(String content) {
        int start = content.indexOf(Tools.division);
        String name = content.substring(0, start);
        if (ASK_BUY.getName().equals(name)) {
            return ASK_BUY;
        }else if(SELL.getName().equals(name)){
            return SELL;
        }else {
            throw new IllegalStateException("get trade exception");
        }
    }

    public static ExecEnum getExec(SellEnum sellEnum) {
        if(sellEnum.equals(SELL)) {
            return ExecEnum.SELL;
        }else if(sellEnum.equals(ASK_BUY)) {
            return ExecEnum.ASK_BUY;
        }else {
            throw new IllegalStateException("get ExecEnum exception");
        }
    }
}
