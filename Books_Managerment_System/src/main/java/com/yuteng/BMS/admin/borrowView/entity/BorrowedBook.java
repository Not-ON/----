package com.yuteng.BMS.admin.borrowView.entity;

public class BorrowedBook {
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

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(int storeCount) {
        this.storeCount = storeCount;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BorrowedBook() {
    }

    public BorrowedBook(String book_id, String book_name, String author, String classify, int storeCount, int borrowCount, String borrower, String email, String startDate, String borrowTime, String state) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author = author;
        this.classify = classify;
        this.storeCount = storeCount;
        this.borrowCount = borrowCount;
        this.borrower = borrower;
        this.email = email;
        this.startDate = startDate;
        this.borrowTime = borrowTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", classify='" + classify + '\'' +
                ", storeCount=" + storeCount +
                ", borrowCount=" + borrowCount +
                ", borrower='" + borrower + '\'' +
                ", email='" + email + '\'' +
                ", startDate='" + startDate + '\'' +
                ", borrowTime='" + borrowTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
