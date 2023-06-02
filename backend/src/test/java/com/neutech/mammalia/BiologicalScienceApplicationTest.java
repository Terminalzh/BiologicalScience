package com.neutech.mammalia;

import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.mapper.sqlProvider.UserSqlProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BiologicalScienceApplicationTest {
    @Test
    void test() {
        System.out.println(UserSqlProvider.inquireUserCountByEmailOrPhone(new User()));
    }
}
