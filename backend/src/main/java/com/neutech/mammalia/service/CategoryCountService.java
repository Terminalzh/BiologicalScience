package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;

import java.util.List;

public interface CategoryCountService {
    int addCategoryCount(CategoryCount categoryCount);

    int deleteCategoryCountById(Integer id);

    int updateCategoryCountById(List<Category> categories);

    CategoryCount inquireCategoryCount(Integer id);

    List<CategoryCount> inquireAllCategories(String expression, Integer start, Integer pageSize);

    List<CategoryCount> inquireCategoryFlatByInheritance(String inheritance);

    CategoryCount inquireCategoryCountById(Integer id);

    String inquireCategorizedInheritanceById(Integer id);

    Integer inquirePageCount(String expression);

}
