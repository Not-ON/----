package com.yuteng.BMS.admin.borrowView.dao;

import com.yuteng.BMS.admin.borrowView.entity.Asker;
import com.yuteng.BMS.admin.borrowView.entity.BorrowedBook;
import com.yuteng.BMS.admin.borrowView.entity.Borrower;
import com.yuteng.BMS.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowDaoImpl implements BorrowDao {

    //审批借阅申请
    @Override
    public boolean borrowApproval(String user_name) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "select user_name,book_name,date,status from borrow_car where user_name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user_name);
        ResultSet resultSet = preparedStatement.executeQuery();

        String userName = resultSet.getString("user_name");
        String status = resultSet.getString("status");

        if (user_name.equals(userName)){
            if (status.equals("不可借阅")){
                String sql1 = "update borrower_car set status = '可借阅' where user_name = ?;";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1,user_name);
                int i = preparedStatement1.executeUpdate();

                if (i>0){
                    return true;
                }else {
                    return false;
                }

            }else if (status.equals("可借阅")){
                return false;
            }
        }

        return false;

    }

    @Override
    public boolean insertAskForm(String user_name) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql1 = "select book_name from borrow_car where user_name = ?; ";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setString(1,user_name);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        String b_name = resultSet1.getString("book_name");

        String sql2 = "select count from library where bookName = ? ;";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setString(1,b_name);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        String b_count = resultSet2.getString("count");

        String sql3 = "select date from borrow_car where user_name = ?" ;//获取借书日期
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        preparedStatement3.setString(1,user_name);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        String start_date = resultSet3.getString("date");

        //插入ask_form表中
        String sql4 = "insert into ask_form (user_name,b_name,b_count,start_date) values (?,?,?,?);";
        PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);

        preparedStatement4.setString(1,user_name);
        preparedStatement4.setString(2,b_name);
        preparedStatement4.setString(3,b_count);
        preparedStatement4.setString(4,start_date);

        int c = preparedStatement4.executeUpdate();
        if (c>0){
            return true;
        }else {
            return false;
        }

    }






    /**
     *查看图书借阅情况
     * 将数据返回给前端
     * 用到borrow_view表
     */
    @Override
    public ArrayList<BorrowedBook> checkBorrowedBook() throws SQLException {
        ArrayList<BorrowedBook> borrowedBookList = new ArrayList<>();

        Connection connection = DBUtil.getConnection();
        String sql = "select book_id,book_name,author,classify,storeCount,borrowCount,borrower,email,startDate,borrowTime,state from borrow_view;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String book_id;
        String book_name;
        String author;
        String classify;
        int storeCount;
        int borrowCount;
        String borrower;
        String email;
        String startDate;
        String borrowTime;
        String state;


        for (int i = 1; ; i+=11){
            if (!resultSet.next()){break;}
            book_id = resultSet.getString("book_id");
            book_name = resultSet.getString("book_name");
            author = resultSet.getString("author");
            classify = resultSet.getString("classify");
            storeCount = resultSet.getInt("storeCount");
            borrowCount = resultSet.getInt("borrowCount");
            borrower = resultSet.getString("borrower");
            email = resultSet.getString("email");
            startDate = resultSet.getString("startDate");
            borrowTime = resultSet.getString("borrowTime");
            state = resultSet.getString("state");

            BorrowedBook borrowedBook = new BorrowedBook(book_id,book_name,author,classify,storeCount,borrowCount,borrower,email,startDate,borrowTime,state);
            borrowedBookList.add(borrowedBook);
        }

        DBUtil.free(resultSet,preparedStatement,connection);
        return  borrowedBookList;
    }

    @Override
    public boolean insertBorrowView(String book_name) throws SQLException {
        Connection connection = DBUtil.getConnection();

        String sql1 = "select author,classify from library where book_name = ?;";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        String author = resultSet1.getString("author");
        String classify = resultSet1.getString("classify");

        String sql2 = "select user_name from ask_form where b_name = ? ";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setString(1,book_name);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        String borrower = resultSet2.getString("user_name");

        String sql3 = "select email from library where username = ? ;";
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        String email = resultSet3.getString("email");

        String sql4 = "select start_date from ask_form where user_name = ?;";
        PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
        ResultSet resultSet4 = preparedStatement4.executeQuery();
        String startDate = resultSet4.getString("start_date");

        LocalDate localDate = LocalDate.now();//获取目前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        long days = 0;
        try {
            long time = sdf.parse(startDate).getTime();
            long time1 = sdf.parse(String.valueOf(localDate)).getTime();

            days = (int)((time1-time1)/(24*60*60*1000));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String borrowTime = "已借" + days + "天";

        String sql5 = "select b_count from ask_form where b_name = ?;";
        PreparedStatement preparedStatement5 = connection.prepareStatement(sql5);
        ResultSet resultSet5 = preparedStatement5.executeQuery();
        int borrowCount = resultSet5.getInt("b_count");

        String sql6 = "select count from library where bookName = ?;";
        PreparedStatement preparedStatement6 = connection.prepareStatement(sql6);
        ResultSet resultSet6 = preparedStatement6.executeQuery();
        int storeCount = resultSet6.getInt("count");

        String state = null;

        String sql7 = "insert into borrow_view (book_name,author,classify,storeCount,borrowCount,borrower,email,startDate,borrowTime,state) values (?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement7 = connection.prepareStatement(sql7);

        preparedStatement7.setString(1,book_name);
        preparedStatement7.setString(2,author);
        preparedStatement7.setString(3,classify);
        preparedStatement7.setInt(4,storeCount);
        preparedStatement7.setInt(5,borrowCount);
        preparedStatement7.setString(6,borrower);
        preparedStatement7.setString(7,email);
        preparedStatement7.setString(8,startDate);
        preparedStatement7.setString(9,borrowTime);
        preparedStatement7.setString(10,state);

        int c = preparedStatement7.executeUpdate();
        if (c>0){
            return true;
        }

        return false;

    }


    /**
     * 查看借阅人身份信息
     *用到ask_form表
     */
    @Override
    public ArrayList<Asker> checkBorrower() throws SQLException {
        ArrayList<Asker> askerList = new ArrayList<>();

        Connection connection = DBUtil.getConnection();
        String sql = "select user_name,b_name,b_count,start_date,state from ask_form; ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String user_name;
        String b_name;
        int b_count;
        String start_date;
        String state;

        for (int i=1; ; i+=5 ){
            if (!resultSet.next()){break;}
            user_name = resultSet.getString("user_name");
            b_name = resultSet.getString("b_name");
            b_count = resultSet.getInt("b_count");
            start_date = resultSet.getString("start_date");
            state = resultSet.getString("state");

            Asker asker = new Asker(user_name,b_name,b_count,start_date,state);
            askerList.add(asker);
        }

        DBUtil.free(resultSet,preparedStatement,connection);

        return askerList;
    }

    @Override
    public ArrayList<Borrower> checkBorrowCar() throws SQLException {
        ArrayList<Borrower> borrowerList = new ArrayList<>();

        Connection connection = DBUtil.getConnection();
        String sql = "select user_name,book_name,author,status from borrow_car where user_name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();


        String user_name ;
        String book_name;
        String author ;
        String status ;

        int i ;
        for (i=1; ; i+=4){
            if (!resultSet.next()){break;}
            user_name = resultSet.getString("user_name");
            book_name = resultSet.getString("book_name");
            author = resultSet.getString("author");
            status = resultSet.getString("status");

            Borrower borrower = new Borrower(user_name,book_name,author,status);
            borrowerList.add(borrower);
        }

        DBUtil.free(resultSet,preparedStatement,connection);

        return borrowerList;
    }


}
