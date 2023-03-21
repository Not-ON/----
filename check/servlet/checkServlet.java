package com.yunteng.user.check.servlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunteng.user.check.service.service;
import com.yunteng.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/checkServlet")
public class checkServlet extends HttpServlet {
    @RequestMapping(value = "/xxx",produces = {"application/json;charset=UTF-8"})
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        service s = new service();
        String userName = "zhangsan";
        try {
            out.print(s.checkAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //循环往复进行.
        while(true){
            String jsonString=String.valueOf(JsonUtil.getJson(req));
            JSONObject jsonObject= JSON.parseObject(jsonString);
            String choice = jsonObject.getString("CHOICE");
            String inputContent = jsonObject.getString("CONTENT");
            try {
                out.print(s.checkBook(inputContent,choice,userName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
