package com.weixin.platform.domain.state;

/**
 * Created by ruanzf on 2015/5/9.
 */
public enum ResponseEnum {
    SOLD_OUT("好的，已经下架啦[玫瑰]"),
    NOGOODS("没有该编号的商品哦[呲牙]"),
    NOT_YOURS("该编号的商品不是你哦[坏笑]"),
    ERROR("系统出错了,程序猿你在哪！！吊起来！！"),
    TO_LONG("亲的建议如滔滔江水有点长呀，最多128个字呢[玫瑰]"),
    SUGGEST("谢谢您给出宝贵的意见或者建议[玫瑰]");

    private String msg;

    ResponseEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
