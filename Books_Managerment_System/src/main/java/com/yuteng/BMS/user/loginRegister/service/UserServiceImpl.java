package com.yuteng.BMS.user.loginRegister.service;

import com.yuteng.BMS.user.loginRegister.dao.UserDao;
import com.yuteng.BMS.user.loginRegister.dao.UserDaoImpl;
import com.yuteng.BMS.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    /**
     * 判断用户能否登录成功
     * @param username
     * @param password
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean isLogin(String username, String password) throws SQLException {
        String pwd = userDao.queryPwd(username);
        if (pwd.equals(password)){
            return true;
        }
        return false;
    }

    /**
     * 判断用户输入的名字所代表的用户是否存在
     * @param username
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean isExist(String username,String email) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from user where username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            //数据库中的名字
            String name = resultSet.getString("username");

            if (name.equals(username)){
                //排除同名的情况
                //数据库里取出的Email
                String Email = resultSet.getString("email");
                if (email.equals(Email)){
                    return true;//存在
                }
            }
        }
        return false;

    }

    @Override
    public boolean isRegister(String username, String password, String dir, String number, String email) throws SQLException {
        if (isExist(username,email)){
            return false;
        }

        //验证密码格式是否正确
        if (password.length()!=8){
            return false;
        }
        return userDao.insert(username,password,dir,number,email);
    }
}
