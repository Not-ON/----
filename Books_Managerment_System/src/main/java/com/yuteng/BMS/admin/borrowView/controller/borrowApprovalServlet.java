package com.yuteng.BMS.admin.borrowView.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuteng.BMS.admin.borrowView.dao.BorrowDao;
import com.yuteng.BMS.admin.borrowView.dao.BorrowDaoImpl;
import com.yuteng.BMS.admin.borrowView.entity.Borrower;
import com.yuteng.BMS.admin.borrowView.entity.Message;
import com.yuteng.BMS.admin.borrowView.service.BorrowService;
import com.yuteng.BMS.admin.borrowView.service.BorrowServiceImpl;
import com.yuteng.BMS.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//审批借阅申请
@WebServlet(name = "borrowApprovalServlet", urlPatterns = "/borrowApprovalServlet")
public class borrowApprovalServlet extends HttpServlet {
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

        BorrowService borrowService = new BorrowServiceImpl();
        BorrowDao borrowDao = new BorrowDaoImpl();

        Connection connection = null;
        try {
        connection = DBUtil.getConnection();

        String state = request.getParameter("state");
        String user_name = request.getParameter("user_name");

        if (state.equals("是")){
            borrowDao.borrowApproval(user_name);

            String sql = "insert into ask_form state values ? ; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"是");
            int i = preparedStatement.executeUpdate();

            Message message = new Message("已通过借阅");
            String jsonString = JSONObject.toJSONString(message);
            writer.write(jsonString);
        }else if (state.equals("否")){
            borrowDao.borrowApproval(user_name);

            String sql1 = "insert into ask_form state values ? ; ";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1,"否");
            int i = preparedStatement1.executeUpdate();

            Message message = new Message("已驳回借阅");
            String jsonString = JSONObject.toJSONString(message);
            writer.write(jsonString);

        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
