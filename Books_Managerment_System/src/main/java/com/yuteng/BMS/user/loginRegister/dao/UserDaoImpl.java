package com.yuteng.BMS.user.loginRegister.dao;

import com.yuteng.BMS.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    /**
     *
     * @param username
     * @return password
     * @throws SQLException
     */
    @Override
    public String queryPwd(String username) throws SQLException {
        String password = null;
        Connection connection = DBUtil.getConnection();
        String sql = "select * from user where username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            password = resultSet.getString("password");
        }
        DBUtil.free(resultSet,preparedStatement,connection);
        return password;
    }

    /**
     * 新用户注册
     * @param username
     * @param password
     * @param dir
     * @param number
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public boolean insert(String username, String password, String dir, String number, String email) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into user (username,password,dir,number,email) values (?,?,?,?,?)" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,dir);
        preparedStatement.setString(4,number);
        preparedStatement.setString(5,email);
        int count = preparedStatement.executeUpdate();
        if (count>0){
            return true;
        }
        DBUtil.free(null,preparedStatement,connection);
        return false;
    }
}
