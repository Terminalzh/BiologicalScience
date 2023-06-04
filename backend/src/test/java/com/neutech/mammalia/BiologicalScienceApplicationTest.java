package com.neutech.mammalia;

import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.mapper.CategoryCountMapper;
import com.neutech.mammalia.mapper.CategoryMapper;
import com.neutech.mammalia.service.CategoryCountService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BiologicalScienceApplicationTest {
    @Resource
    private CategoryCountMapper categoryCountMapper;

    @Test
    void test01() {
        CategoryCount categoryCount = new CategoryCount();
        String s = categoryCountMapper.inquireCategorizedInheritance(1135);
        List<Integer> list = new ArrayList<>(5);
        for (int i = s.split("\\.").length; i < 6; i++) {
            int count;
            count = categoryCountMapper.inquireSubCategoryCount(s + "\\.\\d+".repeat(6 - i));
            list.add(count);
        }
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        categoryCount.setSpecies(list.get(0));
        categoryCount.setGenus(list.get(1));
        categoryCount.setFamily(list.get(2));
        categoryCount.setOrderCount(list.get(3));
        categoryCount.setSubClass(list.get(4));
        System.out.println(categoryCount);
    }
}
