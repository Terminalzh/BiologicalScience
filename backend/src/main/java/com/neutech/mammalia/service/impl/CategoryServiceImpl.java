package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.CategoryMapper;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(List<Category> categories) {
        int count = 0;
        Category parent = categoryMapper.inquireCategoryById(1);
        for (Category category : categories) {
            category.setParentId(parent.getId());
            Category categoryByName = categoryMapper.inquireCategoryByLatinNameAndParentId(category.getParentId(), category.getLatinName());
            if (categoryByName == null) {
                count += categoryMapper.addCategory(category);
            }
            parent = categoryMapper.inquireCategoryByLatinNameAndParentId(category.getParentId(), category.getLatinName());
        }

        return count;
    }

    @Override
    public int deleteCategoryById(Integer id) {
        int count = 0;
        List<Category> categories = categoryMapper.inquireCategoryByParentId(id);
        if (categories.size() == 0) {
            return categoryMapper.deleteCategoryById(id);
        } else {
            for (Category category : categories) {
                count += deleteCategoryById(category.getId());
            }
            count += categoryMapper.deleteCategoryById(id);
        }
        return count;
    }

    @Override
    public int updateCategoryById(Category category) {
        return categoryMapper.updateCategoryById(category);
    }

    @Override
    public Category inquireCategoryById(Integer id) {
        return null;
    }

    @Override
    public List<Category> inquireCategoryByParentId(Integer parentId) {
        return categoryMapper.inquireCategoryByParentId(parentId);
    }

    @Override
    public List<Category> inquireAllCategory() {
        return null;
    }
}
