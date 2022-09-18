package com.getdream.dao;

import com.getdream.pojo.Student;
import com.getdream.pojo.Teacher;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    @Test
    void getTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        Teacher info = mapper.getTeacher(1);

        List<Student> students = info.getStudents();
        System.out.println(info);
        for (Student student : students) {
            System.out.println(student);
        }

        sqlSession.close();
    }

    @Test
    void getInfoList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> infoList = mapper.getInfoList();

        for (Teacher teacher : infoList) {
            System.out.println(teacher);
        }


        sqlSession.close();
    }
}