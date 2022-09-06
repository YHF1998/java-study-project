package com.getdream.dao;

import com.getdream.pojo.User;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class UserDaoTest {

    @Test
    public void test() {

        //获取sqlSession对象
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //方式一：
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> userList = userDao.getUserList();

            //方式二：这种使用率不高，一般都是第一种
            //List<User> userList = sqlSession.selectList("com.getdream.dao.UserDao.getUserList");

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }


    }

    @Test
    public void getUserByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        User info = mapper.getUserByID(2);

        System.out.println(info);

        sqlSession.close();
    }
}