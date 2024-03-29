package org.example.spring.pojo;

import junit.framework.TestCase;
import org.example.spring.utils.ApplicationContextUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

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

    /**
     * 数组类型值的依赖注入
     */
    public void testStudentHobby() {
        Student student = ApplicationContextUtil.applicationContext.getBean("studentNine", Student.class);

        System.out.println(student);
    }

    public void testClazzStudentList() {
        Clazz clazz = ApplicationContextUtil.applicationContext.getBean("clazzTwo", Clazz.class);

        System.out.println(clazz);

    }

    public void testClazzStudentListTwo() {
        Clazz clazzThree = ApplicationContextUtil.applicationContext.getBean("clazzThree", Clazz.class);

        System.out.println(clazzThree);
    }

    /**
     * 使用p命名空间方式的依赖注入demo
     */
    public void testStudent11() {
        Student student11 = ApplicationContextUtil.applicationContext.getBean("student11", Student.class);

        System.out.println(student11);

    }
    
    
    public void testDuibi(){
        float f1 = 3.14f;
        double d1 = f1;

        BigDecimal math1 = new BigDecimal(f1);
        BigDecimal math2 = math1;
        math1 = math1.add(math2);
        
        System.out.println(d1 == f1);
        System.out.println(d1);
        System.out.println(f1);
        System.out.println(math1);
        System.out.println(math2);
        
        
        int i1 = 1;
        i1++;
        
        System.out.println(i1);
        
    }
}
