package org.example.spring.pojo;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest extends TestCase {

    /**
     * 测试 scope属性 singleton和prototype的区别
     * scope属性：取值singleton（默认值），bean在IOC容器中只有一个实例，IOC容器初始化时创建
     * 对象
     * scope属性：取值prototype，bean在IOC容器中可以有多个实例，getBean()时创建对象
     */
    public void  testBeanScope(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        User user = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        user.setAge(18);
        System.out.println(user == user2);
        System.out.println(user.getAge());
        System.out.println(user2.getAge());
        
    }
}