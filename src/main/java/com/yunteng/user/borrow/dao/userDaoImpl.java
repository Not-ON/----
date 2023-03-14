package com.yunteng.user.borrow.dao;

import com.yunteng.user.borrow.entity.BookOfBorrowed;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.yunteng.user.check.entity.Books;
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
        resultSet.next();
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

    @Override
    public void update(String userName, String bookName, String calculate) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update libOfBorrowed set calculate=? where borrower= ? and bookName= ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,calculate);
        preparedStatement.setObject(2,userName);
        preparedStatement.setObject(3,bookName);
        preparedStatement.executeQuery();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
    }
    public int add(List<Books> l, int count, String borrower, String date, String calculate) throws Exception {
        if(l.isEmpty()){
            return 1;
        }
        if(1==updateLibrary(l,count)){
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
        preparedStatement.executeQuery();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return 2;
    }

    @Override
    public int updateLibrary(List<Books> l, int count00) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update Library set count=? where bookName=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Books b = l.get(1);
        int count = b.getCount()-count00;
        if(count<0){
            preparedStatement.setObject(1,0);
            preparedStatement.setObject(2,b.getBookName());
            preparedStatement.executeQuery();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                DBUtil.freeConnection();
            }
            return 1;
        }
        preparedStatement.setObject(1,count);
        preparedStatement.setObject(2,b.getBookName());
        preparedStatement.executeQuery();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return 2;
    }

    /**
     * check new books that agreed
     * @param userName
     * @return
     */
    @Override
    public List<String> checkBorrowCar(String userName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select status ,book_name from borrow_car where user_name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String status;
        String bookName;
        List<String> books = new ArrayList<>();
        for(int i = 1; ;i+=2){
            if(!resultSet.next()){
                break;
            }
            status = resultSet.getString(i);
            bookName = resultSet.getString(i+1);
            if(status == "可借阅"){
                books.add(bookName);
            }
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
        return books;
    }

    @Override
    public void delFromCar(String userName, String bookName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from borrow_car where user_name = ? and book_name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        preparedStatement.setObject(2,bookName);
        preparedStatement.executeQuery();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.freeConnection();
        }
    }
}
