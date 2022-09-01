package com.getdream.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    //初始化
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //销毁
    public void destroy() {
    }

    /**
     * 过滤器的所有代码，在过滤特定请求的时候都会执行
     * 必须要让过滤器继续走下去，不写就会被拦截
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //设置请求内容编码
        request.setCharacterEncoding("utf-8");

        //设置响应编码
        response.setCharacterEncoding("utf-8");

        //设置输出类型
        response.setContentType("text/html;charset=utf-8");

        System.out.println("执行前");
        chain.doFilter(request, response);//必须要让过滤器继续走下去，不写就会被拦截
        System.out.println("执行后");

    }
}
