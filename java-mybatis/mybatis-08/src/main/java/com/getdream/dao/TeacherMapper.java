package com.getdream.dao;

import com.getdream.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    Teacher getTeacher(@Param("id") int id);

    List<Teacher> getInfoList();
}
