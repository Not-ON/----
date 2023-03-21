package com.yuteng.BMS.user.loginRegister.dao;

import java.sql.SQLException;

public interface UserDao {

    /**
     * 查询登录用户名的密码
     * @param username
     * @return  密码
     */
    String queryPwd(String username) throws SQLException;


    boolean insert(String username, String password, String dir, String number, String email) throws SQLException;

}
