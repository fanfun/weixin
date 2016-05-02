package com.weixin.platform.domain;

import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.ProductStateEnum;
import com.weixin.platform.domain.state.TradeEnum;
import com.weixin.platform.wxtools.Tools;

/**
 * Created by Administrator on 2015/5/2.
 */
public class Product {
    private int id;
    private String name;
    private String owner;
    private String describe;
    private String photo;
    private String nick;
    private String phone;
    private int school;
    private int item;
    private int status;
    private int type;
    private long createtime;

    public Product() {
        this.createtime = System.currentTimeMillis();
        this.item = -1;
        this.school = -1;
        this.name = "";
        this.status = ProductStateEnum.OK.getCode();
    }

    public Product(Process process) {
        this.owner = process.getName();
        this.describe = Tools.filterContent(process.getDetail());
        this.photo = process.getPhoto();
        this.createtime = System.currentTimeMillis();
        this.type = ProductEnum.getByCode(process.getSellType()).getId();
        this.item = -1;
        this.name = "";
        this.school = -1;
        this.status = ProductStateEnum.OK.getCode();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
