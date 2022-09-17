package com.getdream.dao;

import com.getdream.pojo.Student;
import com.getdream.pojo.Teacher;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    @Test
    void getTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        Teacher info = mapper.getTeacher(1);

        System.out.println(info);

        sqlSession.close();
    }
}