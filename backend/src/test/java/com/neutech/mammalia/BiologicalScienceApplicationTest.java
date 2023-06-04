package com.neutech.mammalia;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BiologicalScienceApplicationTest {
    @Resource
    private CategoryMapper categoryMapper;

    @Test
    void test01() {
        List<Category> categories = categoryMapper.inquireCategoryByParentId(1077);
        System.out.println(categories);
    }

    @Test
    void test02() {
        Category category = categoryMapper.inquireCategoryById(1094);
        Map<String, Integer> map = countTaxonomy(category);
        System.out.println(map);
    }

    public Map<String, Integer> countTaxonomy(Category category) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("亚纲数量", category.getSubClassCount());
        counts.put("目数量", category.getOrderCount());
        counts.put("科数量", category.getFamilyCount());
        counts.put("属数量", category.getGenusCount());
        counts.put("种数量", category.getSpeciesCount());
        List<Category> categories = categoryMapper.inquireCategoryByParentId(category.getId());
        // 统计子节点的分类数量
        for (Category child : categories) {
            Map<String, Integer> childCounts = countTaxonomy(child);
            for (String key : counts.keySet()) {
                counts.put(key, counts.get(key) + childCounts.get(key));
            }
        }
        return counts;
    }
}
