package com.yuteng.BMS.user.loginRegister.service;

import java.sql.SQLException;

public interface UserService {

    boolean isLogin(String username, String password) throws SQLException;

    boolean isExist(String username,String email) throws SQLException;

    boolean isRegister(String username, String password, String dir, String number, String email) throws SQLException;

}
