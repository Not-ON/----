package com.yuteng.BMS.user.borrow.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class BookOfBorrowed {
    private String bookName;
    private int count;
    private String date;
    private String calculate;
    private String number;
    private String classify;
    private String author;
    private String state;

    public BookOfBorrowed(){
    }

    public BookOfBorrowed(String bookName, int count,String date,String calculate,String number,String classify,String author,String state) {
        setBookName(bookName);
        setCount(count);
        setDate(date);
        setAuthor(author);
        setNumber(number);
        setCalculate(calculate);
        setState(state);
        setClassify(classify);
    }

    @JSONField(name = "NUMBER", ordinal = 1)
    public String getNumber() {
        return number;
    }

    @JSONField(name = "NUMBER", ordinal = 1)
    public void setNumber(String number) {
        this.number = number;
    }

    @JSONField(name = "BOOKNAME", ordinal = 2)
    public String getBookName() {
        return bookName;
    }

    @JSONField(name = "BOOKNAME", ordinal = 2)
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @JSONField(name = "CLASSIFY", ordinal = 3)
    public String getClassify() {
        return classify;
    }

    @JSONField(name = "CLASSIFY", ordinal = 3)
    public void setClassify(String classify) {
        this.classify = classify;
    }

    @JSONField(name = "AUTHOR", ordinal = 4)
    public String getAuthor() {
        return author;
    }

    @JSONField(name = "AUTHOR", ordinal = 4)
    public void setAuthor(String author) {
        this.author = author;
    }

    @JSONField(name = "COUNT", ordinal = 5)
    public int getCount() {
        return count;
    }

    @JSONField(name = "COUNT", ordinal = 5)
    public void setCount(int count) {
        this.count = count;
    }

    @JSONField(name = "DATE", ordinal = 6)
    public String getDate() {
        return date;
    }
    @JSONField(name = "DATE", ordinal = 6)
    public void setDate(String date) {
        this.date = date;
    }
    @JSONField(name = "CALCULATE", ordinal = 7)
    public String getCalculate() {
        return calculate;
    }
    @JSONField(name = "CALCULATE", ordinal = 7)
    public void setCalculate(String calculate) {
        this.calculate = calculate;
    }

    @JSONField(name = "STATE", ordinal = 8)
    public String getState() {
        return state;
    }

    @JSONField(name = "STATE", ordinal = 8)
    public void setState(String state) {
        this.state = state;
    }
}
