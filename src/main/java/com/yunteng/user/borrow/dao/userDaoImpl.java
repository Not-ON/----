package com.yunteng.user.borrow.dao;

import com.yunteng.user.borrow.entity.BookOfBorrowed;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.yunteng.util.*;
public class userDaoImpl implements userDao{

    /**
     * show books that user has borrowed
     * @param userName
     * @return
     */
    @Override
    public List<BookOfBorrowed> show(String userName) throws Exception {
        Connection connection = DBUtil.getConnection();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String dateNow =dateFormat.format(calendar.getTime());
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        Date end;
        Long startTime;
        Long endTime;
        Long num;
        String sql = "Select bookName, date,count,number,classify,author,state from libOfBorrowed where borrower = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BookOfBorrowed> l = new ArrayList<>();
        String bookName;
        String date;
        int count;
        String calculate;
        String number;
        String classify;
        String author;
        String state;
        for (int i = 1; ; i += 7) {
            if (!resultSet.next()) {
                break;
            }
            bookName = resultSet.getString(i);
            date = resultSet.getString(i+1);
            start = (Date) dft.parse(date);
            end = (Date) dft.parse(dateNow);
            startTime = start.getTime();
            endTime = end.getTime();
            num = endTime - startTime;
            count = resultSet.getInt(i+2);
            calculate = "已借"+num +"天";
            number = resultSet.getString(i+3);
            classify = resultSet.getString(i+4);
            author = resultSet.getString(i+5);
            state = resultSet.getString(i+6);
            BookOfBorrowed b = new BookOfBorrowed(bookName, count, date,calculate,number,classify,author,state);
            update(calculate,userName,bookName);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return l;
    }

    @Override
    public void update(String userName, String bookName, String calculate) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update libOfBorrowed set calculate=? where borrower=?, bookName=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,calculate);
        preparedStatement.setObject(2,userName);
        preparedStatement.setObject(3,bookName);
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
    }

}
