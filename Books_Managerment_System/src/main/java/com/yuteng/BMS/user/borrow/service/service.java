package com.yunteng.user.borrow.service;

import com.alibaba.fastjson.JSONObject;
import com.yunteng.user.borrow.dao.userDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class service {
    userDaoImpl u = new userDaoImpl();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar= Calendar.getInstance();

    public service() {
    }
    public String show(String userName) throws Exception {
        //看是否有新加的已通过审批的借阅书.
        updateLib(userName);
        //返回json.
        return JSONObject.toJSONString(u.show(userName));
    }
    public void updateLib(String userName) throws Exception {
        String calculate = "已借0天";
        List<String> books = u.checkBorrowCar(userName);
        String bookName;
        int i = 0;
        if(!books.isEmpty()){
            while(true) {
                bookName = books.get(i);
                u.add(u.queryBook(bookName), 1, userName, calculate);
                i++;
                u.delFromCar(userName,bookName);
                if(i==(books.size())){
                        break;
                }
            }
        }
    }
}
