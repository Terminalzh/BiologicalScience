package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;

import java.util.List;

public interface CategoryService {
    int addCategory(List<Category> categories);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    Category inquireCategoryById(Integer id);

    List<Category> inquireCategoryByParentId(Integer parentId);

    Category inquireCategoryByLatinNameAndParentId(Integer parentId, String latinName);
}
