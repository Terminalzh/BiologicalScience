package com.neutech.mammalia;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.mapper.CategoryCountMapper;
import com.neutech.mammalia.mapper.CategoryMapper;
import com.neutech.mammalia.service.CategoryCountService;
import com.neutech.mammalia.service.CategoryService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class BiologicalScienceApplicationTest {
    @Resource
    private CategoryCountMapper categoryCountMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private CategoryService categoryService;

    private static final String path = "D:\\docxs\\生物分类表t_category.csv";

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
