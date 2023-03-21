package com.yuteng.BMS.admin.borrowView.service;

import com.yuteng.BMS.admin.borrowView.entity.Asker;
import com.yuteng.BMS.admin.borrowView.entity.BorrowedBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowService {

    //给前端返回图书借阅情况
    public String checkBorrowedBook() throws SQLException;

    //给前端返回借阅者状况
    public String checkBorrower() throws SQLException;

}
