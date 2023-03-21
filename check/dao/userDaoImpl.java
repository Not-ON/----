package com.yunteng.user.check.dao;

import com.yunteng.user.check.entity.Books;
import com.yunteng.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDaoImpl implements userDao {
    public List<Books> queryBook1(String classify) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classify,author,publisher,bookLocation,content,state,count from library where classify like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        classify = "%" + classify + "%";
        preparedStatement.setObject(1, classify);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Books> l = new ArrayList<>();
        String bookName;
        int count;
        String content;
        String img;
        String number;
        String author;
        String publisher;
        String bookLocation;
        String state;
        int i;
        for (i = 1; ; ) {
            if (!resultSet.next()) {
                break;
            }
            img = resultSet.getString(i);
            number = resultSet.getString(i + 1);
            bookName = resultSet.getString(i + 2);
            classify = resultSet.getString(i + 3);
            author = resultSet.getString(i + 4);
            publisher = resultSet.getString(i + 5);
            bookLocation = resultSet.getString(i + 6);
            content = resultSet.getString(i + 7);
            state = resultSet.getString(i + 8);
            count = resultSet.getInt(i + 9);
            Books b = new Books(img, number, bookName, classify, author, publisher, bookLocation, content, count, state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet, preparedStatement, connection);
        }
        return l;
    }

    @Override
    public List<Books> queryBook2(String bookName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classify,author,publisher,bookLocation,content,state,count from library where bookName like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        bookName = "%" + bookName + "%";
        preparedStatement.setObject(1, bookName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Books> l = new ArrayList<>();
        int count;
        String content;
        String classify;
        String img;
        String number;
        String author;
        String publisher;
        String bookLocation;
        String state;
        int i;
        for (i = 1; ; ) {
            if (!resultSet.next()) {
                break;
            }
            img = resultSet.getString(i);
            number = resultSet.getString(i + 1);
            bookName = resultSet.getString(i + 2);
            classify = resultSet.getString(i + 3);
            author = resultSet.getString(i + 4);
            publisher = resultSet.getString(i + 5);
            bookLocation = resultSet.getString(i + 6);
            content = resultSet.getString(i + 7);
            state = resultSet.getString(i + 8);
            count = resultSet.getInt(i + 9);
            Books b = new Books(img, number, bookName, classify, author, publisher, bookLocation, content, count, state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet, preparedStatement, connection);
        }
        return l;
    }

    public List<Books> queryAll() throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classify,author,publisher,bookLocation,content,state,count from library ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Books> l = new ArrayList<>();
        String bookName;
        int count;
        String content;
        String classify;
        String img;
        String number;
        String author;
        String publisher;
        String bookLocation;
        String state;
        int i;
        for (i = 1; ; ) {
            if (!resultSet.next()) {
                break;
            }
            img = resultSet.getString(i);
            number = resultSet.getString(i + 1);
            bookName = resultSet.getString(i + 2);
            classify = resultSet.getString(i + 3);
            author = resultSet.getString(i + 4);
            publisher = resultSet.getString(i + 5);
            bookLocation = resultSet.getString(i + 6);
            content = resultSet.getString(i + 7);
            state = resultSet.getString(i + 8);
            count = resultSet.getInt(i + 9);
            Books b = new Books(img, number, bookName, classify, author, publisher, bookLocation, content, count, state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet, preparedStatement, connection);
        }
        return l;
    }
    public void insertTo(String bookName, String userName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Insert into borrow_car (user_name,book_name,status,date) values (?,?,?,now());";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        preparedStatement.setObject(2, bookName);
        preparedStatement.setObject(3, "不可借阅");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    @Override
    public boolean isAgreed(String bookName, String userName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select status from borrow_car where user_name = ? and book_name = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        preparedStatement.setObject(2, bookName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                DBUtil.free(resultSet, preparedStatement, connection);
            }
            return true;
        } else {
            if (connection.getAutoCommit()) {
                DBUtil.free(resultSet, preparedStatement, connection);
            }
        }
        return false;
    }

}