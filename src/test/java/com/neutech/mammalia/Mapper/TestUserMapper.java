package com.neutech.mammalia.Mapper;

import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TestUserMapper {
    @Autowired
    UserMapper mapper;

    @Test
    void testAddUser() {
        int count = 0;
        Map<Integer, User> map = new HashMap<>();
        while (map.size() < 1000) {
            User user = getUser();
            map.put(user.getId(), user);
        }
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            count += mapper.addUser(map.get(key));
        }
    }


    User getUser() {
        User user = new User();
        user.setId((int) (Math.random() * 100000));
        user.setName(UUID.randomUUID().toString().substring(0, 10));
        user.setPassword(UUID.randomUUID().toString().substring(0, 10));
        user.setGender(Math.random() > 0.5 ? "male" : "female");
        user.setPhone("1" + (int) (Math.random() * 1000000000));
        user.setCreateTime(new Date());
        return user;
    }
}
