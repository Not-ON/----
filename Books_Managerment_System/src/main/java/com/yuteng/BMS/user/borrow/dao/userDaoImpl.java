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
     * 用于展示已经通过借阅的书
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public List<BookOfBorrowed> show(String userName) throws Exception {
        Connection connection = DBUtil.getConnection();
        //计算已经借阅的时间
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String dateNow =dateFormat.format(calendar.getTime());
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date start;
        java.util.Date end;
        Long startTime;
        Long endTime;
        Long num;
        String sql = "Select bookName, date,count,number,classify,author,state from libofborrowed where borrower = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BookOfBorrowed> l = new ArrayList<>();
        String bookName;
        java.util.Date date;
        int count;
        String calculate;
        String number;
        String classify;
        String author;
        String state;
        for (int i = 1; ; ) {
            if (!resultSet.next()) {
                break;
            }
            bookName = resultSet.getString(i);
            date = new java.util.Date(resultSet.getDate(i+1).getTime());
            start = date;
            end = dft.parse(dateNow);
            startTime = start.getTime();
            endTime = end.getTime();
            num = endTime - startTime;
            count = resultSet.getInt(i+2);
            calculate = "已借"+num/86400000 +"天";
            number = resultSet.getString(i+3);
            classify = resultSet.getString(i+4);
            author = resultSet.getString(i+5);
            state = resultSet.getString(i+6);
            BookOfBorrowed b = new BookOfBorrowed(bookName, count, date.toString(),calculate,number,classify,author,state);
            update(calculate,userName,bookName);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return l;
    }

    /**
     * 从library表中查询对应bookname的信息.返回一个列表
     * @param bookName
     * @return
     * @throws Exception
     */
    public List<Books> queryBook(String bookName) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Select count,number,classify,author from library where bookName = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, bookName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Books> l = new ArrayList<>();
        int count;
        String number;
        String classify;
        String author;
        if(resultSet.next()){
            count = resultSet.getInt( 1);
            number = resultSet.getString( 2);
            classify = resultSet.getString( 3);
            author = resultSet.getString( 4);
            Books b = new Books(number,bookName,classify,author,count);
            l.add(b);
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return l;
    }

    /**
     * 在不同时间更新已借阅的时间
     * @param userName
     * @param bookName
     * @param calculate
     * @throws Exception
     */
    @Override
    public void update(String userName, String bookName, String calculate) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update libofborrowed set calculate=? where borrower= ? and bookName= ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,calculate);
        preparedStatement.setObject(2,userName);
        preparedStatement.setObject(3,bookName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ResultSet resultSet = null;
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
    }

    /**
     * 在合适的时机（.即有已经审批但是还没有加入借阅的表中.）向借阅列表中加入借阅信息.
     * @param l
     * @param count
     * @param borrower
     * @param calculate
     * @return
     * @throws Exception
     */
    public int add(List<Books> l, int count, String borrower, String calculate) throws Exception {
        if(l.isEmpty()){
            return 1;
        }
        if(1==updateLibrary(l,count)){
            return 1;
        }
        Books b =l.get(0);
        Connection connection = DBUtil.getConnection();
        String sql = "Insert into libofborrowed (borrower,count,number,bookName,classify,author,date,state,calculate) values (?,?,?,?,?,?,now(),?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,borrower);
        preparedStatement.setObject(2,count);
        preparedStatement.setObject(3,b.getNumber());
        preparedStatement.setObject(4,b.getBookName());
        preparedStatement.setObject(5,b.getClassify());
        preparedStatement.setObject(6,b.getAuthor());
        preparedStatement.setObject(7,"等待归还");
        preparedStatement.setObject(8,calculate);
        ResultSet resultSet =null;
        preparedStatement.executeUpdate();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return 2;
    }

    @Override
    public int updateLibrary(List<Books> l, int count00) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "Update library set count=? where bookName=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Books b = l.get(0);
        int count = b.getCount()-count00;
        if(count<0){
            preparedStatement.setObject(1,0);
            preparedStatement.setObject(2,b.getBookName());
            ResultSet resultSet =null;
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                DBUtil.free(resultSet,preparedStatement,connection);
            }
            return 1;
        }
        preparedStatement.setObject(1,count);
        preparedStatement.setObject(2,b.getBookName());
        ResultSet resultSet = null;
        preparedStatement.executeUpdate();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
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
        for(int i = 1; ;){
            if(!resultSet.next()){
                break;
            }
            status = resultSet.getString(i);
            bookName = resultSet.getString(i+1);
            if(status .equals( "可借阅")){
                books.add(bookName);
            }
        }
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
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
        ResultSet resultSet =null;
        preparedStatement.executeUpdate();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
    }
}
