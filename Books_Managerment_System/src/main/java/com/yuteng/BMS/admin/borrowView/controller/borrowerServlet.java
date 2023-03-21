package com.yuteng.BMS.admin.borrowView.controller;

import com.yuteng.BMS.admin.borrowView.dao.BorrowDao;
import com.yuteng.BMS.admin.borrowView.dao.BorrowDaoImpl;
import com.yuteng.BMS.admin.borrowView.entity.Asker;
import com.yuteng.BMS.admin.borrowView.entity.BorrowedBook;
import com.yuteng.BMS.admin.borrowView.entity.Borrower;
import com.yuteng.BMS.admin.borrowView.service.BorrowService;
import com.yuteng.BMS.admin.borrowView.service.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


//直接给前端返回借阅者的数据
@WebServlet(name = "borrowerServlet", urlPatterns = "/borrowerServlet")
public class borrowerServlet extends HttpServlet {
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

        BorrowService borrowService = new BorrowServiceImpl();
        BorrowDao borrowDao = new BorrowDaoImpl();

        ArrayList<Borrower> borrowers = null;

        try {
            borrowers = borrowDao.checkBorrowCar();

            for (int i=0;i<borrowers.size();i++){
            String user_name = borrowers.get(i).getUser_name();
            borrowDao.insertAskForm(user_name);
            }

            borrowService.checkBorrower();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
