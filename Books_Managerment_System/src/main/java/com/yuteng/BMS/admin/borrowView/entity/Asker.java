package com.yuteng.BMS.admin.borrowView.entity;

public class Asker {
    String user_name;

    String b_name;

    int b_count;

    String start_date;

    String state;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public int getB_count() {
        return b_count;
    }

    public void setB_count(int b_count) {
        this.b_count = b_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Asker() {
    }

    public Asker(String user_name, String b_name, int b_count, String start_date, String state) {
        this.user_name = user_name;
        this.b_name = b_name;
        this.b_count = b_count;
        this.start_date = start_date;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Asker{" +
                "user_name='" + user_name + '\'' +
                ", b_name='" + b_name + '\'' +
                ", b_count=" + b_count +
                ", start_date='" + start_date + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
