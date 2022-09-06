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

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.addUser(new User(5, "xixi", "241234"));

        if (i > 0) {
            System.out.println("插入成功");
        }


        //提交事务  增删改都需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }

    @Test
    void updateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.updateUser(new User(5, "chenchen", "chenchen"));

        if (i > 0) {
            System.out.println("更新成功");
        }

        //提交事务
        sqlSession.commit();

        sqlSession.close();

    }
}