package org.example.spring.pojo;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest extends TestCase {
    public void test(){
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean
        Student studentOne = (Student) applicationContext.getBean("studentOne");

        studentOne.setSid(1);

        System.out.println(studentOne);
    }
}