package com.yunteng.user.borrow.service;

import com.alibaba.fastjson.JSONObject;
import com.yunteng.user.borrow.dao.userDaoImpl;

public class service {
    userDaoImpl u = new userDaoImpl();

    public service() {
    }
    public String show(String userName) throws Exception {
        return JSONObject.toJSONString(u.show(userName));
    }
}
