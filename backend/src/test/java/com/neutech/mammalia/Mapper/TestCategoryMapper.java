package com.neutech.mammalia.Mapper;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TestCategoryMapper {
    @Autowired
    CategoryMapper mapper;

    @Test
    void testAddCategory() {
        Map<Integer, Category> map = new HashMap<>();
        int count = 0;
        while (map.size() < 1000) {
            Category category = getCategory();
            map.put(category.getId(), category);
        }
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            count += mapper.addCategory(map.get(key));
        }
        System.out.println(count);
    }

    @Test
    void testDeleteCategoryById() {
        System.out.println(mapper.deleteCategoryById(1));
    }

    @Test
    void testUpdateCategoryById() {
        Category category = new Category(2, "Class 1", "Order 1", "Family 1", "Genus 1", "Species 1");
        System.out.println(mapper.updateCategoryById(category));
    }

    @Test
    void testInquireCategoryById() {
        Category category = mapper.inquireCategoryById(3);
        System.out.println(category);
    }

    @Test
    void testInquireAllCategory() {
        List<Category> categories = mapper.inquireAllCategory();
        System.out.println(categories.size());
    }

    Category getCategory() {
        Random random = new Random();
        Category category = new Category();
        category.setId(random.nextInt());
        category.setClazz("Class " + random.nextInt(10));
        category.setOrders("Order " + random.nextInt(10));
        category.setFamily("Family " + random.nextInt(10));
        category.setGenus("Genus " + random.nextInt(10));
        category.setSpecies("Species " + random.nextInt(10));
        return category;
    }
}
