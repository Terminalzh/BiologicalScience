package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.CategoryFlat;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.CategoryCountMapper;
import com.neutech.mammalia.service.CategoryCountService;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
@Transactional
public class CategoryCountServiceImpl implements CategoryCountService {
    @Resource
    private CategoryCountMapper categoryCountMapper;
    @Lazy
    @Resource
    private CategoryService categoryService;

    @Override
    public int addCategoryCount(CategoryCount categoryCount) {
        return categoryCountMapper.addCategoryCount(categoryCount);
    }

    @Override
    public int deleteCategoryCountById(Integer id) {
        return categoryCountMapper.deleteCategoryCountById(id);
    }

    @Override
    public int updateCategoryCountById(List<Category> categories) {
        int count = 0;
        for (Category category : categories) {
            CategoryCount categoryCount = this.inquireCategoryCount(category.getId());
            count += categoryCountMapper.updateCategoryCountById(categoryCount);
        }
        CategoryCount categoryCount = inquireCategoryCount(1);
        count += categoryCountMapper.updateCategoryCountById(categoryCount);
        return count;
    }

    @Override
    public CategoryCount inquireCategoryCount(Integer id) {
        CategoryCount categoryCount = new CategoryCount();
        String s = categoryCountMapper.inquireCategorizedInheritance(id);
        if (s == null)
            return null;
        List<Integer> list = new ArrayList<>();
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
        categoryCount.setId(id);
        return categoryCount;
    }

    @Override
    public List<CategoryCount> inquireAllCategories(String expression, Integer start, Integer pageSize) {
        return categoryCountMapper.inquireAllCategories(expression, start, pageSize);
    }

    @Override
    public List<CategoryCount> inquireCategoryFlatByInheritance(String inheritance) {
        return categoryCountMapper.inquireCategoryFlatByInheritance(inheritance);
    }

    @Override
    public CategoryCount inquireCategoryCountById(Integer id) {
        return categoryCountMapper.inquireCategoryCountById(id);
    }


    @Override
    public String inquireCategorizedInheritanceById(Integer id) {
        return categoryCountMapper.inquireCategorizedInheritance(id);
    }

    @Override
    public Integer inquirePageCount(String expression) {
        return categoryCountMapper.inquirePageCount(expression);
    }
}
