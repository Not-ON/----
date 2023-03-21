package com.yuteng.BMS.user.loginRegister.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuteng.BMS.user.loginRegister.entity.Code;
import com.yuteng.BMS.user.loginRegister.entity.User;
import com.yuteng.BMS.user.loginRegister.service.UserService;
import com.yuteng.BMS.user.loginRegister.service.UserServiceImpl;

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

        PrintWriter writer = response.getWriter();

        InputStreamReader insr = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while (respInt!=-1){
            body.append((char) respInt);
            respInt = insr.read();
        }

        insr.close();

        writer.print(body);

        User user = JSONObject.parseObject(body.toString(), User.class);

        writer.write(user.toString());

        String username = user.getUsername();
        String password = user.getPassword();

        try {
            if (userService.isLogin(username,password)){


                if (username.equals("admin") && password.equals("admin")){
                    //跳转管理员端

                    Code code = new Code(000,"欢迎来到管理员端");
                    String jsonString = JSONObject.toJSONString(code);
                    writer.write(jsonString);

                }
                //跳转书库页面

                Code code = new Code(001,"欢迎来到书库");
                String jsonString = JSONObject.toJSONString(code);
                writer.write(jsonString);

            }else {

                Code code = new Code(-1,"用户名或密码错误");
                String jsonString = JSONObject.toJSONString(code);
                writer.write(jsonString);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
