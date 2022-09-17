package com.getdream.pojo;

import lombok.*;


//虽然下面的注解提供了两种构造器类型，想要其他构造器的话可以自己定义

//无参构造
//@NoArgsConstructor
//全参构造
//@AllArgsConstructor
//没有使用构造器注解时，生成无参构造/get/set/tostring/hashcode 等方法
@Data
public class User {
    private int id;
    private String name;
    private String pwd;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
