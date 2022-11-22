package org.example.spring.controller.impl;

import org.example.spring.controller.UserController;
import org.example.spring.service.UserService;

public class UserControllerImpl implements UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void saveUser() {
        this.userService.saveUser();
    }
}
