package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int deleteUserById(Integer id);

    int updateUserById(User user);

    User inquireUserById(Integer id);

    User inquireUserByEmailOrPhone(User user);

    List<User> inquireAllUser();
}
