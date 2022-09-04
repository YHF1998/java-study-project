package com.getdream.servlet;

import com.getdream.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("admin") && password.equals("123456")) {
            req.getSession().setAttribute(Constant.USER_SESSION, req.getSession().getId());
            //跳转
            resp.sendRedirect(req.getContextPath() + "/main.jsp");
        } else {
            //登录失败
            resp.sendRedirect(req.getContextPath() + "/common/error.jsp");
        }


    }
}
