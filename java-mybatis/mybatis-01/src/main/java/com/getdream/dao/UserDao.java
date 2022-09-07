package com.getdream.dao;


import com.getdream.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    //查询列表
    List<User> getUserList();

    //根据id查询信息
    User getUserByID(int id);

    int addUser(User user);

    int addUserWithMap(Map<String,Object> map);

    int updateUser(User user);

    int deleteUserByID(int id);


}
