package com.getdream.servlet;

import com.getdream.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object session = req.getSession().getAttribute(Constant.USER_SESSION);
        if (session != null) {
            req.getSession().removeAttribute(Constant.USER_SESSION);
        }

        //跳转
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
