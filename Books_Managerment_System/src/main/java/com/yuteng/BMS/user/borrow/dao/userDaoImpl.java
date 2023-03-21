package com.yuteng.BMS.user.borrow.dao;


import com.yuteng.BMS.user.borrow.entity.BookOfBorrowed;
import com.yuteng.BMS.user.check.entity.Books;
import com.yuteng.BMS.utils.DBUtil;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class userDaoImpl implements userDao {

    /**
     * show books that user has borrowed
     * 用于展示已借阅的列表.返回列表.
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
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return l;
    }


    /**
     * search for book
     * 用于查找书籍，返回指定元素.
     * @param bookName
     * @return a list of books
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return l;
    }

    /**
     * update time
     * 用于更新已借阅的时间
     * @param userName
     * @param bookName
     * @param calculate
     * @throws Exception
     */
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
            DBUtil.free(null,preparedStatement,connection);
        }
    }



    /**
     * adda book into libOfBorrowed. use update().
     * 用于在libofborrow表中添加借阅数据.
     * @param l
     * @param count
     * @param borrower
     * @param date
     * @param calculate
     * @return 1->false 2->success.useless return.
     * @throws Exception
     */
    public int add(List<Books> l, int count, String borrower, String date, String calculate) throws Exception {
        if(l.isEmpty()){//查找的书名是否为空，为空返回1
            return 1;
        }
        if(1==updateLibrary(l,count)){//1->false
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
            DBUtil.free(null,preparedStatement,connection);
        }
        return 2;
    }


    /**
     * update Library.
     * 用于在library表中更新数据
     * @param l
     * @param count00
     * @return 1->false 2->success.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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
                DBUtil.free(null,preparedStatement,connection);
            }
            return 1;
        }
        preparedStatement.setObject(1,count);
        preparedStatement.setObject(2,b.getBookName());
        preparedStatement.executeQuery();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            DBUtil.free(null,preparedStatement,connection);
        }
        return 2;
    }

    /**
     * check new books that agreed
     * 用于展示borrow_car表.返回其中已通过审批但是未添加到libofborrow表中的数据
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

        if (connection.getAutoCommit()) {
            DBUtil.free(resultSet,preparedStatement,connection);
        }
        return books;
    }


    /**
     * delete books that have added
     * 从borrow_car表里删除添加到libofborrow的.以免重复添加.
     * @param userName
     * @param bookName
     * @throws Exception
     */
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
            DBUtil.free(null,preparedStatement,connection);
        }
    }
}
