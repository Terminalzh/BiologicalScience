package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.mapper.UserMapper;
import com.neutech.mammalia.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public User inquireUserById(Integer id) {
        return userMapper.inquireUserById(id);
    }

    @Override
    public User inquireUserByName(String name) {
        return userMapper.inquireUserByName(name);
    }

    @Override
    public User inquireUserCountByEmailOrPhone(User user) {
        return userMapper.inquireUserCountByEmailOrPhone(user);
    }

    @Override
    public List<User> inquireAllUser() {
        return userMapper.inquireAllUsers();
    }
}
