package com.yunteng.user.borrow.servlet;
import com.yunteng.user.borrow.service.service;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        /**
         * 传入的数据 用户名 书名 数量
         */
        PrintWriter out = resp.getWriter();
        service s = new service();
        String userName = (String) req.getSession().getAttribute("user");
        try {
            out.print(s.show(userName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
