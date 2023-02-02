package com.yunteng.user.check.dao;

import com.yunteng.user.check.entity.Books;

import java.sql.SQLException;
import java.util.List;

public interface userDao {
    /**
     * search for book
     * @param bookName
     * @return a list of books
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryBook(String bookName) throws Exception;
    /**
     * search for book a
     * @param classify
     * @return a list of books-->classify
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryBook1(String classify) throws Exception;
    /**
     *
     */
    public List<Books> queryBook2(String bookName) throws Exception;

    /**
     * search all
     * @return a list of all books
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Books> queryAll() throws Exception;

    /**
     * adda book into libOfBorrowed. use update().
     * @param l
     * @param count
     * @param borrower
     * @param date
     * @param calculate
     * @return 1->false 2->success
     * @throws Exception
     */
    public int add(List<Books> l, int count, String borrower, String date, String calculate) throws Exception;

    /**
     * update Library
     * @param l
     * @param count00
     * @return 1->false 2->success
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int update(List<Books> l, int count00) throws Exception;
}
