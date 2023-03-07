package com.yuteng.BMS.user.loginRegister.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (uri.contains("/Login.jsp") || uri.contains("/Register.jsp") || uri.contains("/admin.jsp")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (uri.contains("/js")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (uri.contains("/LoginServlet")||uri.contains("/RegisterServlet")) {
            filterChain.doFilter(request, response);
            return;
        }

        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            filterChain.doFilter(request,response);
            return;
        }

        response.sendRedirect("Login.jsp");
    }

    @Override
    public void destroy() {

    }
}
