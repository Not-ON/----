package com.yunteng.user.check.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Books {
    private String img;
    private String number;
    private String bookName;
    private String classify;
    private String author;
    private String publisher;
    private String date;
    private String content;
    private int count;
    private String state;

    public Books() {

    }

    public Books(String img, String number, String bookName, String classify, String author, String publisher, String date, String content, int count, String state) {
        this.img = img;
        this.number = number;
        this.bookName = bookName;
        this.classify = classify;
        this.author = author;
        this.publisher = publisher;
        this.date = date;
        this.content = content;
        this.count = count;
        this.state = state;
    }

    public Books(String number, String bookName, String classify, String author, int count) {
        this.number = number;
        this.bookName = bookName;
        this.classify = classify;
        this.author = author;
        this.count = count;
    }

    @JSONField(name = "IMG", ordinal = 1)
    public String getImg() {
        return img;
    }

    @JSONField(name = "IMG", ordinal = 1)
    public void setImg(String img) {
        this.img = img;
    }

    @JSONField(name = "NUMBER", ordinal = 2)
    public String getNumber() {
        return number;
    }

    @JSONField(name = "NUMBER", ordinal = 2)
    public void setNumber(String number) {
        this.number = number;
    }

    @JSONField(name = "BOOKNAME", ordinal = 3)
    public String getBookName() {
        return bookName;
    }

    @JSONField(name = "BOOKNAME", ordinal = 3)
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @JSONField(name = "CLASSIFY", ordinal = 4)
    public String getClassify() {
        return classify;
    }

    @JSONField(name = "CLASSIFY", ordinal = 4)
    public void setClassify(String classify) {
        this.classify = classify;
    }

    @JSONField(name = "AUTHOR", ordinal = 5)
    public String getAuthor() {
        return author;
    }

    @JSONField(name = "AUTHOR", ordinal = 5)
    public void setAuthor(String author) {
        this.author = author;
    }

    @JSONField(name = "PUBLISHER", ordinal = 6)
    public String getPublisher() {
        return publisher;
    }

    @JSONField(name = "PUBLISHER", ordinal = 6)
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JSONField(name = "DATE", ordinal = 7)
    public String getDate() {
        return date;
    }

    @JSONField(name = "DATE", ordinal = 7)
    public void setDate(String date) {
        this.date = date;
    }

    @JSONField(name = "CONTENT", ordinal = 8)
    public String getContent() {
        return content;
    }

    @JSONField(name = "CONTENT", ordinal = 8)
    public void setContent(String content) {
        this.content = content;
    }

    @JSONField(name = "STATE", ordinal = 9)
    public String getState() {
        return state;
    }

    @JSONField(name = "STATE", ordinal = 9)
    public void setState(String state) {
        this.state = state;
    }

    @JSONField(name = "COUNT", ordinal = 10)
    public int getCount() {
        return count;
    }

    @JSONField(name = "COUNT", ordinal = 10)
    public void setCount(int count) {
        this.count = count;
    }
}