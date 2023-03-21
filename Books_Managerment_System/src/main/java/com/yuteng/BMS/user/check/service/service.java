package com.yunteng.user.check.service;
import com.alibaba.fastjson.JSONObject;
import com.yunteng.user.check.entity.errorUntity;
import com.yunteng.user.check.dao.userDaoImpl;
public class service {
    userDaoImpl u = new userDaoImpl();
    errorUntity er = new errorUntity();
    public String checkAll() throws Exception {
        return JSONObject.toJSONString(u.queryAll());
    }
    public String checkBook(String inputContent,String choice,String userName) throws Exception {
        switch (choice){
            case "classify":{
                return JSONObject.toJSONString(u.queryBook1(inputContent));
            }
            case "name":{
                return JSONObject.toJSONString(u.queryBook2(inputContent));
            }
            case "borrow":{
                return JSONObject.toJSONString(borrow(userName,inputContent));
            }
        }
        return "error";
    }
    public errorUntity borrow(String userName,String bookName) throws Exception {
        if(u.isAgreed(bookName,userName)){
            er.setMessage("不可重复借阅");
            return er;
        }
       else {
            u.insertTo(bookName,userName);
            er.setMessage("申请发送成功");
            return er;
       }
    }
}
