package com.weixin.platform.domain.state;

import com.weixin.platform.wxtools.Tools;


/**
 * Created by ruanzf on 2015/5/9.
 */
public enum ShuntEnum {
    NONE("NONE", Tools.NONE, null),
    SOLD_OUT("SOLD_OUT", "下架", ExecEnum.SOLD_OUT),
    SUGGEST("SUGGEST", "意见", ExecEnum.SUGGEST),
    MANUAL("MANUAL", "手册", ExecEnum.MANUAL),
    RESET("RESET", "清空", ExecEnum.RESET),
    REINFO("REINFO", "设置", ExecEnum.REINFO),
    ;

    private String code;
    private String name;
    private ExecEnum execEnum;

    ShuntEnum(String code, String name, ExecEnum execEnum) {
        this.code = code;
        this.name = name;
        this.execEnum = execEnum;
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
        if(content.equals(MANUAL.getName())) {
            return true;
        }
        if(!Tools.validate(content)) {
            return false;
        }
        int start = content.indexOf(Tools.division);
        String name = content.substring(0, start);
        for(ShuntEnum shuntEnum : ShuntEnum.values()) {
            if(shuntEnum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ExecEnum getExecEnum() {
        return execEnum;
    }

    public void setExecEnum(ExecEnum execEnum) {
        this.execEnum = execEnum;
    }

    public static ExecEnum shunt(String content) {
        if(content.equals(MANUAL.getName())) {
            return MANUAL.execEnum;
        }
        int start = content.indexOf(Tools.division);
        if(start <= 0) {
            return ExecEnum.SHUNT;
        }
        String name = content.substring(0, start);
        for(ShuntEnum shuntEnum : ShuntEnum.values()) {
            if(shuntEnum.getName().equals(name)) {
                return shuntEnum.execEnum;
            }
        }
        return ExecEnum.SHUNT;
    }
}
