package org.example.spring.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

    public static ClassPathXmlApplicationContext applicationContext;

    static {
        //获取ioc容器
        applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
    }


}
