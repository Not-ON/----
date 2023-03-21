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
        String dir = user.getDir();
        String number = user.getNumber();
        String email = user.getEmail();
        String invitationCode = user.getInvitationCode();

        //内部邀请码验证
        if (invitationCode.equals("YDSY")){
            try {
                if (userService.isRegister(username,password,dir,number,email)){

                    Code code = new Code(002,"注册成功");
                    String jsonString = JSONObject.toJSONString(code);
                    writer.write(jsonString);
                }else {

                    Code code = new Code(-2,"注册失败");
                    String jsonString = JSONObject.toJSONString(code);
                    writer.write(jsonString);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (invitationCode.equals("")){

            Code code = new Code(-3,"验证码不能为空");
            String jsonString = JSONObject.toJSONString(code);
            writer.write(jsonString);
        } else if (!invitationCode.equals("YDSY")) {

            Code code = new Code(-4,"验证码错误");
            String jsonString = JSONObject.toJSONString(code);
            writer.write(jsonString);
        }

    }
}
