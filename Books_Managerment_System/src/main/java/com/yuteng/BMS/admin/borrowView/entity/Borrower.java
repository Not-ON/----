package com.yuteng.BMS.admin.borrowView.entity;

//借阅者信息

public class Borrower {
    //用户名
    String user_name;

    //书名
    String book_name;


    //作者
    String author;

    //"可借阅" 或 "不可借阅"
    String status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Borrower(String user_name, String book_name, String author, String status) {
        this.user_name = user_name;
        this.book_name = book_name;
        this.author = author;
        this.status = status;
    }

    public Borrower() {
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "user_name='" + user_name + '\'' +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
