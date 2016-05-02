//package com.weixin.platform.domain.state;
//
//import com.weixin.platform.wxtools.Tools;
//
///**
// * Created by ruanzf on 2015/4/22.
// */
//public enum AskBuyEnum {
//    NONE("NONE", Tools.NONE),
//    ASK_BUY("ASK_BUY", "求购")
//    ;
//
//    private String code;
//    private String name;
//
//    AskBuyEnum(String code, String name) {
//        this.code = code;
//        this.name = name;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public static boolean me(String content) {
//        if(!Tools.validate(content)) {
//            return false;
//        }
//        int start = content.indexOf(Tools.division);
//        String name = content.substring(0, start);
//        if(ASK_BUY.getName().equals(name)) {
//            return true;
//        }
//        return false;
//    }
//
//}
