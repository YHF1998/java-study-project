package com.getdream.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountListener implements HttpSessionListener {

    /**
     * session创建
     *
     * @param se
     */
    public void sessionCreated(HttpSessionEvent se) {

        //获取上下文
        ServletContext context = se.getSession().getServletContext();

        System.out.println(se.getSession().getId());

        //读取自定义的键值
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            //自动装箱和拆箱
            int count = onlineCount;
            onlineCount = count + 1;
        }

        context.setAttribute("OnlineCount", onlineCount);

    }

    /**
     * session销毁
     *
     * @param se
     */
    public void sessionDestroyed(HttpSessionEvent se) {
//获取上下文
        ServletContext context = se.getSession().getServletContext();

        //读取自定义的键值
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");

        se.getSession().invalidate();

        if (onlineCount == null) {
            onlineCount = 0;
        } else {
            //自动装箱和拆箱
            int count = onlineCount;
            onlineCount = count - 1;
        }

        context.setAttribute("OnlineCount", onlineCount);
    }
}
