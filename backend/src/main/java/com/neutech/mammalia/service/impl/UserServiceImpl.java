package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.mapper.UserMapper;
import com.neutech.mammalia.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserById(Integer id) {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }

    @Override
    public User inquireUserById(Integer id) {
        return null;
    }

    @Override
    public User inquireUserByName(String name) {
        return null;
    }

    @Override
    public List<User> inquireAllUser() {
        return null;
    }
}
