package org.example.spring.pojo;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest extends TestCase {

    public void testSayHello() {
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //通过注册的id获取实体类
        Hello hello = (Hello) applicationContext.getBean("hello");

        hello.sayHello();
    }
}