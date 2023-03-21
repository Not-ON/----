package com.yuteng.BMS.admin.borrowView.dao;

import com.yuteng.BMS.admin.borrowView.entity.Asker;
import com.yuteng.BMS.admin.borrowView.entity.BorrowedBook;
import com.yuteng.BMS.admin.borrowView.entity.Borrower;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowDao {
    //审批借阅申请
    public boolean borrowApproval(String user_name) throws SQLException;

    //将借阅人信息添加到ask_form表中
    public boolean insertAskForm(String user_name) throws SQLException;



    /**
     *查看图书借阅情况
     * 将数据返回给前端
     * 用到borrow_view表
     */
    public ArrayList<BorrowedBook> checkBorrowedBook() throws SQLException;


    public boolean insertBorrowView (String book_name) throws SQLException;

    /**
     * 查看借阅人身份信息
     *将数据返回给前端
     */
    public ArrayList<Asker> checkBorrower() throws SQLException;


    public ArrayList<Borrower> checkBorrowCar() throws SQLException;


}
