package com.getdream.dao;

import com.getdream.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
    @Select("select * from javaweb.teacher where id=#{id}")
    Teacher getTeacher(@Param("id") int id);
}
