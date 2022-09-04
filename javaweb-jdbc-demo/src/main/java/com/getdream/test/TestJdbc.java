package com.getdream.test;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //配置信息
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3400/javaweb?useUnicode=true&characterEncoding=utf-8";

        String username = "root";
        String password = "example";


        //1.加载驱动
        //Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //3.向数据库发送sql的对象statement, 预处理可以用prepareStatement
        Statement statement = connection.createStatement();


        //4.编写sql
        String sql = "select * from `javaweb`.`users` limit 10";

        //5.执行sql,返回结果集
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birthday=" + resultSet.getObject("birthday"));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
