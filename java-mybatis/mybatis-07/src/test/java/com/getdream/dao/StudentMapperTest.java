package com.getdream.dao;

import com.getdream.pojo.Student;
import com.getdream.pojo.Teacher;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    @Test
    void getInfoByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student info = mapper.getInfoByID(1);

        System.out.println(info);

        sqlSession.close();

    }

    @Test
    void getInfoList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> infoList = mapper.getInfoList();

        for (Student student : infoList) {
            System.out.println(student);
            Teacher teacher = student.getTeacher();
            System.out.println(teacher);
        }

        sqlSession.close();
    }

    @Test
    void getInfoList2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> infoList = mapper.getInfoList2();

        for (Student student : infoList) {
            System.out.println(student);
            Teacher teacher = student.getTeacher();
            System.out.println(teacher);
        }

        sqlSession.close();
    }
}