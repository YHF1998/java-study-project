package com.getdream.dao;


import com.getdream.pojo.User;

import java.util.List;

public interface UserDao {

    //查询列表
    List<User> getUserList();

    //根据id查询信息
    User getUserByID(int id);

    int addUser(User user);

    int updateUser(User user);
}
