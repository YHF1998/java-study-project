package com.getdream.dao;

import com.getdream.pojo.Student;

import java.util.List;

public interface StudentMapper {
    Student getInfoByID(int id);

    List<Student> getInfoList();

    List<Student> getInfoList2();

}
