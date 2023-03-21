package com.yuteng.BMS.user.loginRegister.entity;

import lombok.Data;

@Data
public class User {

    /**
     * 用户名
     */
    String username;

    /**
     * 密码
     */
    String password;

    /**
     * 方向
     */
    String dir;

    /**
     * 期数
     */
    String number;

    /**
     * 邮箱
     */
    String email;

    /**
     * 邀请码
     */
    String invitationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dir='" + dir + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                '}';
    }
}
