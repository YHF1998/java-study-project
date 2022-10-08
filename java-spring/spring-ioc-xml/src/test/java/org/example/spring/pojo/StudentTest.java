package org.example.spring.pojo;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest extends TestCase {
    public void test() {
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean 方式一 ：使用xml绑定的id获取
        //Student student = (Student) applicationContext.getBean("studentOne");

        //获取bean 方式二 ：根据类型来获取 这种在实际开发中是使用最多的，因为没有人会重复声明同一个class
        //使用这种方式，需要保证xml里面对应的class只绑定了一次，不然的话，这里使用class获取bean，框架是不知道到你要获取的是哪个bean
        //Student student = applicationContext.getBean(Student.class);

        //获取bean 方式三 ：使用xml绑定的id+类型来获取
        //这种方式，能够有效避免方式二所出现的问题
        Student student = applicationContext.getBean("studentOne", Student.class);


        student.setSid(1);

        System.out.println(student);
    }

    /**
     * 依赖注入
     * 通过xml配置property实现属性值的依赖注入（默认值设置）
     */
    public void testDI() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean
        Student student = applicationContext.getBean("studentTwo", Student.class);

        System.out.println(student);
    }

    /**
     * 有参构造bean xml配置调用
     */
    public void testConstructArg() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean
        Student student = applicationContext.getBean("studentThree", Student.class);

        System.out.println(student);
    }

    /**
     * 依赖注入，设置null
     */
    public void testSetNullConstruct() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean
        Student student = applicationContext.getBean("studentFour", Student.class);
        student.setGender("中性");
        System.out.println(student.getGender().toString());
    }

    /**
     * 依赖注入，特殊字符处理
     */
    public void testCDATA() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        //获取bean
        Student student = applicationContext.getBean("studentFive", Student.class);
        System.out.println(student);
    }

    public void testClazz() {
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        Student student = applicationContext.getBean("studentSix", Student.class);

        System.out.println(student);
    }

    public void testClazzTwo() {
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        Student student = applicationContext.getBean("studentSeven", Student.class);

        System.out.println(student);
    }

    public void testClazzThree() {
        //获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

        Student student = applicationContext.getBean("studentEight", Student.class);

        System.out.println(student);
    }
}