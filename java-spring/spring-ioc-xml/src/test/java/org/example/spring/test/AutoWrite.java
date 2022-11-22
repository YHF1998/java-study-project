package org.example.spring.test;

import org.example.spring.controller.UserController;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWrite {

    @Test
    public void testAutowrite() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowrite.xml");

        UserController bean = applicationContext.getBean(UserController.class);

        bean.saveUser();

    }
}
