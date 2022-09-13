package com.getdream.pojo;


//user实体类
public class User {

    private int id;
    private String name;

    //实体类字段为password 数据表为pwd，出现了问题：pwd字段结果为空 User{id=1, name='a', pwd='null'}
    //处理方式：
    //一：sql直接字段别名，不细说
    //二：com/getdream/dao/UserMapper.xml 里面设置结果集映射

    private String password;

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + password + '\'' +
                '}';
    }
}
