package com.weixin.platform.domain;

/**
 * Created by Administrator on 2015/5/1.
 */
public class Owner {
    private String nick;
    private String phone;
    private int id;
    private int type;

    public Owner() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
