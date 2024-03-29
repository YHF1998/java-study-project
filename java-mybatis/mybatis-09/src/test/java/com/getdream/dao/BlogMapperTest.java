package com.getdream.dao;

import com.getdream.pojo.Blog;
import com.getdream.utils.IDUtils;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BlogMapperTest {

    @Test
    void addBlog() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);


        //方式一
        //Blog blog = new Blog();
        //blog.setTitle("测试标题1");
        //blog.setId(IDUtils.getId());
        //blog.setAuthor("测试作者1");
        //blog.setViews(1);
        //blog.setCreateTime(new Date());
        //int i = mapper.addBlog(blog);

        //方式二
        int i = mapper.addBlog(new Blog(IDUtils.getId(), "测试标题3", "测试作者3", new Date(), 0));


        if (i > 0) {
            System.out.println("插入成功：" + i);
        }

        sqlSession.close();
    }
    
    @Test
    void testEcho() {
        byte b2 = 65; // 系统会自动把 65 当成 byte 类型处理
        int a = 12;
        byte b  = (byte)a; //强制转换，在超过了byte范围时，会转换为0
        System.out.println(a);
        System.out.println(b);
        
        Short s = 5;
        System.out.println(s);
        System.out.println(s.getClass());
        System.out.println(s.getClass() == java.lang.Short.class);
        System.out.println(s.getClass() == Short.class);
        
    }
}