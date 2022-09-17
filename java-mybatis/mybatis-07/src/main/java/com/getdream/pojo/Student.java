package com.getdream.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    //学生关联对应老师
    private Teacher teacher;
}
