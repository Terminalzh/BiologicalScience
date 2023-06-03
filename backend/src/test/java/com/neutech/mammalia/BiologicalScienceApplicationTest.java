package com.neutech.mammalia;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BiologicalScienceApplicationTest {
    @Resource
    private CategoryMapper categoryMapper;

    @Test
    void test() {
        List<Category> categories = categoryMapper.inquireCategoryByParentId(1077);
        System.out.println(categories);
    }
}
