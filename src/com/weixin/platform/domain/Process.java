package com.weixin.platform.domain;

import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.wxtools.Tools;

/**
 * Created by ruanzf on 2015/4/22.
 */
public class Process {

    private String state;  //MainFlow
    private String tradeType;
    private String sellType;  //是否执行完
    private String detail;
    private String name;
    private String photo;

    public Process() {
        this.state = ExecEnum.SHUNT.getName();
        this.sellType = "";
        this.tradeType = "";
        this.detail = "";
        this.name = "";
        this.photo = "";
    }

    public Process(String state) {
        this.state = state;
        this.sellType = "";
        this.tradeType = "";
        this.detail = "";
        this.name = "";
        this.photo = "";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void appendPicture(String pic) {
        if(photo == null || photo.equals("")) {
            photo = pic;
        }else {
            this.photo = this.photo + Tools.pic + pic;
        }
    }

    public int pictureSize() {
        if(photo != null && !photo.equals("")) {
            int size = this.photo.split(Tools.pic).length;
            return size;
        }
        return 0;
    }
}
