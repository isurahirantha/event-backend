package com.codeloon.ems.service;

import com.codeloon.ems.model.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> getAllUsers();

    UserBean findByUsername(String userName);
}
