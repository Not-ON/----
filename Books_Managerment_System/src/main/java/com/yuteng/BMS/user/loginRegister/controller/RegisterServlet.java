package com.yuteng.BMS.user.loginRegister.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuteng.BMS.user.loginRegister.service.UserService;
import com.yuteng.BMS.user.loginRegister.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    JSONObject object = new JSONObject();

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dir = request.getParameter("dir");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String invitationCode = request.getParameter("invitationCode");

        PrintWriter writer = response.getWriter();

        //内部邀请码验证
        if (invitationCode.equals("YDSY")){
            try {
                if (userService.isRegister(username,password,dir,number,email)){
                    writer.write("注册成功");
                    //object.put("String","注册成功");
                }else {
                    writer.write("注册失败");
                    //object.put("String","注册失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (invitationCode.equals("")){
            writer.write("验证码不能为空");
            //object.put("String","验证码不能为空");
        } else if (!invitationCode.equals("YDSY")) {
            writer.write("验证码错误");
            //object.put("String","验证码错误");
        }

    }
}
