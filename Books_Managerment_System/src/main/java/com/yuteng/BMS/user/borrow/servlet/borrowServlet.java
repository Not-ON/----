package com.yunteng.user.borrow.servlet;
import com.yunteng.user.borrow.service.service;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
    @RequestMapping(value = "/xxx",produces = {"application/json;charset=UTF-8"})
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        service s = new service();
        String userName = "zhangsan";
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
