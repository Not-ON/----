package com.yuteng.BMS.user.loginRegister.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class User {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 方向
     */
    private String dir;

    /**
     * 期数
     */
    private String number;

    /**
     * 邮箱
     */
    private String email;

    public User() {
    }

    public User(String username, String password, String dir, String number, String email) {
        this.username = username;
        this.password = password;
        this.dir = dir;
        this.number = number;
        this.email = email;
    }

    @JSONField(name = "USERNAME",ordinal = 1)
    public String getUsername() {
        return username;
    }

    @JSONField(name = "USERNAME",ordinal = 1)
    public void setUsername(String username) {
        this.username = username;
    }

    @JSONField(name = "PASSWORD",ordinal = 2)
    public String getPassword() {
        return password;
    }

    @JSONField(name = "PASSWORD",ordinal = 2)
    public void setPassword(String password) {
        this.password = password;
    }

    @JSONField(name = "DIR",ordinal = 3)
    public String getDir() {
        return dir;
    }

    @JSONField(name = "DIR",ordinal = 3)
    public void setDir(String dir) {
        this.dir = dir;
    }

    @JSONField(name = "NUMBER",ordinal = 4)
    public String getNumber() {
        return number;
    }

    @JSONField(name = "NUMBER",ordinal = 4)
    public void setNumber(String number) {
        this.number = number;
    }

    @JSONField(name = "EMAIL",ordinal = 5)
    public String getEmail() {
        return email;
    }

    @JSONField(name = "EMAIL",ordinal = 5)
    public void setEmail(String email) {
        this.email = email;
    }
}
