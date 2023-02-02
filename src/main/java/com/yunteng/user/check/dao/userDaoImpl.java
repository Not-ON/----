package com.yunteng.user.check.dao;

import com.yunteng.user.check.entity.Books;
import com.yunteng.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDaoImpl implements userDao {

    @Override
    public List<Books> queryBook(String bookName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select count,number,classify,author from Library where bookName = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, bookName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Books> l = new ArrayList<>();
        int count;
        String number;
        String classify;
        String author;
        count = resultSet.getInt( 1);
        number = resultSet.getString( 2);
        classify = resultSet.getString( 3);
        author = resultSet.getString( 4);
        Books b = new Books(number,bookName,classify,author,count);
        l.add(b);
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return l;
    }

    public List<Books> queryBook1(String classify) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classifyauthor,publisher,date,content,state,count from Library where classify like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        classify = "%"+ classify + "%";
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
        String date;
        String state;
        int i;
        for (i = 1; ; i += 10) {
            if (!resultSet.next()) {break;}
            img = resultSet.getString(i);
            number = resultSet.getString(i+1);
            bookName = resultSet.getString(i+2);
            classify = resultSet.getString(i+3);
            author = resultSet.getString(i+4);
            publisher = resultSet.getString(i+5);
            date = resultSet.getString(i+6);
            content = resultSet.getString(i+7);
            state = resultSet.getString(i+8);
            count = resultSet.getInt(i+9);
            Books b = new Books(img,number,bookName,classify,author,publisher,date,content,count,state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return l;
    }

    @Override
    public List<Books> queryBook2(String bookName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classify,author,publisher,date,content,state,count from Library where bookName like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        bookName = "%"+ bookName + "%";
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
        String date;
        String state;
        int i;
        for (i = 1; ; i += 10) {
            if (!resultSet.next()) {break;}
            img = resultSet.getString(i);
            number = resultSet.getString(i+1);
            bookName = resultSet.getString(i+2);
            classify = resultSet.getString(i+3);
            author = resultSet.getString(i+4);
            publisher = resultSet.getString(i+5);
            date = resultSet.getString(i+6);
            content = resultSet.getString(i+7);
            state = resultSet.getString(i+8);
            count = resultSet.getInt(i+9);
            Books b = new Books(img,number,bookName,classify,author,publisher,date,content,count,state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return l;
    }

    public List<Books> queryAll() throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select img,number,bookName,classify,author,publisher,date,content,state,count from Library ;";
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
        String date;
        String state;
        int i;
        for (i = 1; ; i += 10) {
            if (!resultSet.next()) {break;}
            img = resultSet.getString(i);
            number = resultSet.getString(i+1);
            bookName = resultSet.getString(i+2);
            classify = resultSet.getString(i+3);
            author = resultSet.getString(i+4);
            publisher = resultSet.getString(i+5);
            date = resultSet.getString(i+6);
            content = resultSet.getString(i+7);
            state = resultSet.getString(i+8);
            count = resultSet.getInt(i+9);
            Books b = new Books(img,number,bookName,classify,author,publisher,date,content,count,state);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return l;
    }

    @Override
    public int add(List<Books> l, int count, String borrower, String date, String calculate) throws Exception {
        if(l.isEmpty()){
            return 1;
        }
        if(1==update(l,count)){
            return 1;
        }
        Books b =l.get(1);
        Connection connection = DBUtil.getConnection();
        String sql = "Insert into libOfBorrowed (borrower,count,number,bookName,classify,author,date,state,calculate) values (?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,borrower);
        preparedStatement.setObject(2,count);
        preparedStatement.setObject(3,b.getNumber());
        preparedStatement.setObject(4,b.getBookName());
        preparedStatement.setObject(5,b.getClassify());
        preparedStatement.setObject(6,b.getAuthor());
        preparedStatement.setObject(7,date);
        preparedStatement.setObject(8,"等待归还");
        preparedStatement.setObject(9,calculate);
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return 2;
    }

    @Override
    public int update(List<Books> l, int count00) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update Library set count=? where bookName=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Books b = l.get(1);
        int count = b.getCount()-count00;
        if(count<0){
            preparedStatement.setObject(1,0);
            preparedStatement.setObject(2,b.getBookName());
            return 1;
        }
        preparedStatement.setObject(1,count);
        preparedStatement.setObject(2,b.getBookName());
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return 2;
    }
}