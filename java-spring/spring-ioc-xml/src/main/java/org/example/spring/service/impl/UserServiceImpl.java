package org.example.spring.service.impl;

import org.example.spring.dao.UserDao;
import org.example.spring.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        this.userDao.saveUser();
    }
}
