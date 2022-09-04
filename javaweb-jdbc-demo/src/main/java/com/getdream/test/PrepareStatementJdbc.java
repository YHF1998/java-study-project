package com.getdream.test;

import java.sql.*;

public class PrepareStatementJdbc {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //设置连接
        String url = "jdbc:mysql://localhost:3400/javaweb?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "example";

        //使用反射加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection connection = DriverManager.getConnection(url, username, password);

        //编写sql
        String sql = "select * from `javaweb`.`users` where `id`>? limit 10";

        //预处理
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //填充参数
        preparedStatement.setInt(1, 1);

        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birthday=" + resultSet.getObject("birthday"));
        }

        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
