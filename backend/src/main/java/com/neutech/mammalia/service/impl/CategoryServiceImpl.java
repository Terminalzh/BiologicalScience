package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.CategoryMapper;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(Category category) {
        return 0;
    }

    @Override
    public int deleteCategoryById(Integer id) {
        return 0;
    }

    @Override
    public int updateCategoryById(Category category) {
        return 0;
    }

    @Override
    public Category inquireCategoryById(Integer id) {
        return null;
    }

    @Override
    public List<Category> inquireAllCategory() {
        return null;
    }
}
