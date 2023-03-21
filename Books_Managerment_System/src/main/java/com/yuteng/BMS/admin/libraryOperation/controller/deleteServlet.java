package com.yuteng.BMS.admin.libraryOperation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuteng.BMS.admin.libraryOperation.entity.Book;
import com.yuteng.BMS.admin.libraryOperation.entity.Message;
import com.yuteng.BMS.admin.libraryOperation.service.AdminService;
import com.yuteng.BMS.admin.libraryOperation.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(name = "deleteServlet", urlPatterns = "/deleteServlet")
public class deleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理post请求乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        AdminService adminService = new AdminServiceImpl();
        PrintWriter writer = response.getWriter();

        InputStreamReader insr = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while (respInt!=-1){
            body.append((char) respInt);
            respInt = insr.read();
        }

        insr.close();

        Book book = JSON.parseObject(body.toString(),Book.class);
        String bookName = book.getBookName();

        try {
            if (adminService.delete(bookName)){

                Message message = new Message(002,"删除成功");
                String jsonString = JSONObject.toJSONString(message);
                writer.write(jsonString);
            }else {

                Message message = new Message(-2,"删除失败");
                String jsonString = JSONObject.toJSONString(message);
                writer.write(jsonString);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
