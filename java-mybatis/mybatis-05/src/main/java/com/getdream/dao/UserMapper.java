package com.getdream.dao;


import com.getdream.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    //mybatis 的注解方式，只时候在简单sql上用，复杂的还是推荐使用xml方式
    @Select("select * from user where id > #{id}")
    List<User> getList(int id);

}
