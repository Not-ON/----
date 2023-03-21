package com.yuteng.BMS.admin.libraryOperation.entity;

public class Book {
    String img;//封面
    String number;//书籍编号
    String bookName;//书名
    String classify;//书籍类型
    String author;//作者
    String publisher;//出版社
    String bookLocation;//书籍位置
    String content;//简介
    String state;//状态 申请借阅 待审批
    int count;//库存xx本

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(String bookLocation) {
        this.bookLocation = bookLocation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "img='" + img + '\'' +
                ", number='" + number + '\'' +
                ", bookName='" + bookName + '\'' +
                ", classify='" + classify + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", bookLocation='" + bookLocation + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", count=" + count +
                '}';
    }
}
