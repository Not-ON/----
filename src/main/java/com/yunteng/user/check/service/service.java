package com.yunteng.user.check.service;
import com.alibaba.fastjson.JSONObject;
import com.yunteng.user.check.entity.errorUntity;
import com.yunteng.user.check.dao.userDaoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class service {
    userDaoImpl u = new userDaoImpl();
    errorUntity er = new errorUntity();
    Calendar calendar= Calendar.getInstance();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
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
                return borrow(userName,inputContent,1);
            }
        }
        return "error";
    }
    public String borrow(String userName,String bookName,int count) throws Exception {
        String date =dateFormat.format(calendar.getTime());
        String calculate = "已借0天";
        switch(u.add(u.queryBook(bookName),count,userName,date,calculate)){
            case 1:{
                er.setMessage("借书失败");
                return JSONObject.toJSONString(er.getMessage());
            }
            case 2:{
                er.setMessage("借书成功");
                return JSONObject.toJSONString(er.getMessage());
            }
        }
        return "error";
    }
}
