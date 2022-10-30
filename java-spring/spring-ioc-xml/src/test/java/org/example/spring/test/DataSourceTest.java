package org.example.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class DataSourceTest {


    /**
     * 获取资源
     */
    @Test
    public void testDataSource() throws SQLException {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-datasource.xml");

        DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);

        System.out.println(dataSource.getConnection());

    }

}
