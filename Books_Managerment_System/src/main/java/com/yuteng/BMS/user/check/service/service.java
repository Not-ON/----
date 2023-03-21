package com.yuteng.BMS.user.check.service;

import com.alibaba.fastjson.JSONObject;

import com.yuteng.BMS.user.check.dao.userDaoImpl;
import com.yuteng.BMS.user.check.entity.errorUntity;

public class service {
    userDaoImpl u = new userDaoImpl();
    errorUntity er = new errorUntity();

    //展示全部的书
    public String checkAll() throws Exception {
        return JSONObject.toJSONString(u.queryAll());
    }


    public String checkBook(String inputContent,String choice,String userName) throws Exception {
        switch (choice){
            case "classify":{//根据分类查书
                return JSONObject.toJSONString(u.queryBook1(inputContent));//inputContent相当于classify
            }
            case "name":{//通过书名查书
                return JSONObject.toJSONString(u.queryBook2(inputContent));//inputContent相当于bookName
            }
            case "borrow":{
                return borrow(userName,inputContent);
            }
        }
        return "error";
    }

    public String borrow(String userName,String bookName) throws Exception {
        if(u.isArgeed(bookName,userName)){//管理端已通过借阅
            er.setMessage("不可重复借阅");
            return JSONObject.toJSONString(er.getMessage());
        }
        else {//管理端还未通过借阅
            er.setMessage("申请发送成功");
            return JSONObject.toJSONString(er.getMessage());
        }
    }
}
