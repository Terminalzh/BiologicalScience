package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;

import java.util.List;

public interface CategoryService {
    int addCategory(List<Category> categories);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    Category inquireCategoryById(Integer id);

    List<Category> inquireCategoryByParentId(Integer parentId);

    Category inquireCategoryByLatinNameAndParentId(Integer parentId, String latinName);

    CategoryCount inquireCategoryCountById(Integer id);
}
