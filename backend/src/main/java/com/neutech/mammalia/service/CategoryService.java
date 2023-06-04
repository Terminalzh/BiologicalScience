package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;

import java.util.List;

public interface CategoryService {
    int addCategory(List<Category> categories);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    List<Category> inquireCategoryByParentId(Integer parentId);
}
