package com.weixin.platform.domain;

/**
 * Created by Administrator on 2015/5/1.
 */
public class Student {
    private String name;
    private String nick;
    private String phone;
    private int school;
    private String time;
    private int visit;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", school=" + school +
                ", time=" + time +
                ", visit='" + visit + '\'' +
                '}';
    }
}
