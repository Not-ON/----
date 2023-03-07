package com.yuteng.BMS.user.loginRegister.controller;

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


@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserServiceImpl();


        // 处理post请求乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();

        try {
            if (userService.isLogin(username,password)){

                if (username.equals("admin") && password.equals("admin")){
                    //跳转管理员端

                }
                //跳转书库页面

            }else {
                writer.write("用户名或密码错误");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
