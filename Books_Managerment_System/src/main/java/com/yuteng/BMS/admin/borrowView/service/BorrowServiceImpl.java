package com.yuteng.BMS.admin.borrowView.service;

import com.alibaba.fastjson.JSONObject;
import com.yuteng.BMS.admin.borrowView.dao.BorrowDao;
import com.yuteng.BMS.admin.borrowView.dao.BorrowDaoImpl;

import java.sql.SQLException;

public class BorrowServiceImpl implements BorrowService{

    BorrowDao borrowDao = new BorrowDaoImpl();

    //给前端返回图书借阅情况
    @Override
    public String checkBorrowedBook() throws SQLException {
        return JSONObject.toJSONString(borrowDao.checkBorrowedBook());
    }

    //给前端返回借阅者状况
    @Override
    public String checkBorrower() throws SQLException {
        return JSONObject.toJSONString(borrowDao.checkBorrower());
    }
}
