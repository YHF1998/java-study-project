package com.getdream.dao;

import com.getdream.pojo.User;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
//log4j
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;


class UserDaoTest {

    //log4j
    //static Logger logger = Logger.getLogger(UserMapper.class);

    //log4j2
    static Logger logger = LogManager.getLogger(UserMapper.class);

    @Test
    public void getUserByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User info = mapper.getUserByID(1);

        System.out.println(info);

        sqlSession.close();
    }

    @Test
    void log4jTest() {
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
    }

    @Test
    void log4j2Test() {
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
    }
}