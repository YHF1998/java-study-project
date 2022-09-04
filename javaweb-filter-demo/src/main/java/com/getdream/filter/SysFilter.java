package com.getdream.filter;

import com.getdream.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//判断是否登录
public class SysFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //类型转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Object userSession = req.getSession().getAttribute(Constant.USER_SESSION);
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/common/error.jsp");
        }

        //走下去
        chain.doFilter(request, response);

    }

    public void destroy() {

    }
}
