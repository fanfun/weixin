package com.weixin.platform.domain;

/**
 * Created by ruanzf on 2015/5/9.
 */
public class Suggest {

    private int id;
    private int school;
    private String owner;
    private String detail;
    private long createtime;

    public Suggest() {
        this.school = -1;
        this.createtime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
