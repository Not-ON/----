package com.yunteng.user.borrow.dao;

import com.yunteng.user.borrow.entity.BookOfBorrowed;

import java.sql.SQLException;
import java.util.List;

public interface userDao {


    /**
     * show books that user has borrowed
     * @param userName
     * @return a list of books
     */
    public List<BookOfBorrowed> show(String userName) throws Exception;

    /**
     * update time
     * @param userName
     * @param bookName
     * @param caculate
     * @throws Exception
     */
    public void update(String userName,String bookName,String caculate) throws Exception;

}
