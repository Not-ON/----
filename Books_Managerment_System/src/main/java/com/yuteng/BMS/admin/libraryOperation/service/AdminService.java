package com.yuteng.BMS.admin.libraryOperation.service;

import java.sql.SQLException;

public interface AdminService {

    //添加书籍
    boolean insert(String img,String number,String bookName,String classify,String author,String publisher,String bookLocation,String content,int count) throws SQLException;

    //修改书籍信息
    boolean update(String img,String number,String bookName,String classify,String author,String publisher,String bookLocation,String content,int count) throws SQLException;

    //删除书籍，数据库中整列删除
    boolean delete(String bookName) throws SQLException;


}
