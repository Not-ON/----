package com.yuteng.BMS.user.check.dao;


import com.yuteng.BMS.user.check.entity.Books;

import java.sql.SQLException;
import java.util.List;

public interface userDao {
    /**
     * search for book
     * 通过分类找书.
     * @param classify
     * @return a list of books-->classify
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryBook1(String classify) throws Exception;

    /**
     *通过书名找书
     */
    public List<Books> queryBook2(String bookName) throws Exception;


    /**
     * search all
     * 展示全部的书.
     * @return a list of all books
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryAll() throws Exception;


    /**
     * check if this book is agreed .
     * 添加数据到borrow_car表中.
     * @param bookName
     * @param userName
     * @return a result of status
     */
    public boolean isArgeed(String bookName,String userName) throws Exception;

}
