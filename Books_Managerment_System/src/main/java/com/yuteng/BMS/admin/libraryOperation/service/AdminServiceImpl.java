package com.yuteng.BMS.admin.libraryOperation.service;

import com.yuteng.BMS.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService{
    /**
     * 添加书籍
     * @param img
     * @param number
     * @param bookName
     * @param classify
     * @param author
     * @param publisher
     * @param
     * @param content
     * @param
     * @param
     * @return
     * @throws SQLException
     */
    @Override
    public boolean insert(String img, String number, String bookName, String classify, String author, String publisher, String bookLocation, String content, int count) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into library (img,number,bookName,classify,author,publisher,bookLocation,content,count) values (?,?,?,?,?,?,?,?,?); ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,img);
        preparedStatement.setString(2,number);
        preparedStatement.setString(3,bookName);
        preparedStatement.setString(4,classify);
        preparedStatement.setString(5,author);
        preparedStatement.setString(6,publisher);
        preparedStatement.setString(7,bookLocation);
        preparedStatement.setString(8,content);
        preparedStatement.setInt(9,count);


        int c = preparedStatement.executeUpdate();

        DBUtil.free(null,preparedStatement,connection);
        if (c>0){
            return true;
        }else {
            return false;
        }


    }

    /**
     * 更改书籍属性
     * @param img
     * @param number
     * @param bookName
     * @param classify
     * @param author
     * @param publisher
     * @param
     * @param content
     * @param
     * @param count
     * @return
     * @throws SQLException
     */
    @Override
    public boolean update(String img, String number, String bookName, String classify, String author, String publisher, String bookLocation, String content, int count) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "update library set img=?,number=?,bookName=?,classify=?,author=?,publisher=?,bookLocation=?,content=?,count=? where bookName=?; ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,img);
        preparedStatement.setString(2,number);
        preparedStatement.setString(3,bookName);
        preparedStatement.setString(4,classify);
        preparedStatement.setString(5,author);
        preparedStatement.setString(6,publisher);
        preparedStatement.setString(7,bookLocation);
        preparedStatement.setString(8,content);
        preparedStatement.setInt(9,count);
        preparedStatement.setString(10,bookName);

        int c = preparedStatement.executeUpdate();
        DBUtil.free(null,preparedStatement,connection);
        if (c>0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 删除书籍相关内容，整行删除
     * @param bookName
     * @return
     * @throws SQLException
     */
    @Override
    public boolean delete(String bookName) throws SQLException {
        Connection connection = DBUtil.getConnection();

        String sql = "delete from library where bookName = ?; ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,bookName);

        int c = preparedStatement.executeUpdate();

        DBUtil.free(null,preparedStatement,connection);

        if (c>0){
            return true;
        }else {
            return false;
        }

    }
}
