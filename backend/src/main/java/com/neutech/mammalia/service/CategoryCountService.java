package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;

import java.util.List;

public interface CategoryCountService {
    int addCategoryCount(List<Category> categories);

    int deleteCategoryCountById(Integer id);

    int updateCategoryCountById(List<Category> categories);

    CategoryCount inquireCategoryCount(Integer id);

    String inquireCategorizedInheritance(Integer id);

}
