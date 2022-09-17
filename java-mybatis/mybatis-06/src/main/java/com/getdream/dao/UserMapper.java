package com.getdream.dao;


import com.getdream.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    //mybatis 的注解方式，只时候在简单sql上用，复杂的还是推荐使用xml方式
    @Select("select * from user where id > #{id}")
    List<User> getList(int id);


    //参数注解
    //下面sql注解的匹配项 #{id} 在参数有注解id和参数变量id2时，优先匹配id
    //如果把注解id改id2，很明显就会发现查询失败
    @Select("select * from user where id = #{id}")
    User getInfoByID(@Param("id") int id2);

}
