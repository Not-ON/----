package com.yunteng.user.borrow.dao;

import com.yunteng.user.borrow.entity.BookOfBorrowed;
import com.yunteng.user.check.entity.Books;

import java.sql.SQLException;
import java.util.List;

public interface userDao {


    /**
     * show books that user has borrowed
     * 用于展示已借阅的列表.返回列表.
     * @param userName
     * @return a list of books
     */
    public List<BookOfBorrowed> show(String userName) throws Exception;
    /**
     * search for book
     * 用于查找书籍，返回指定元素.
     * @param bookName
     * @return a list of books
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryBook(String bookName) throws Exception;

    /**
     * update time
     * 用于更新已借阅的时间
     * @param userName
     * @param bookName
     * @param caculate
     * @throws Exception
     */

    public void update(String userName,String bookName,String caculate) throws Exception;
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
    public int add(List<Books> l, int count, String borrower, String date, String calculate) throws Exception;
    /**
     * update Library.
     * 用于在library表中更新数据
     * @param l
     * @param count00
     * @return 1->false 2->success.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int updateLibrary(List<Books> l, int count00) throws Exception;

    /**
     * check new books that agreed
     * 用于展示borrow_car表.返回其中已通过审批但是未添加到libofborrow表中的数据
     * @param userName
     * @return
     */
    public List<String> checkBorrowCar(String userName) throws Exception;

    /**
     * delete books that have added
     * 从borrow_car表里删除添加到libofborrow的.以免重复添加.
     * @param userName
     * @param bookName
     * @throws Exception
     */
    public void delFromCar(String userName,String bookName) throws Exception;
}
