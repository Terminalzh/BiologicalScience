package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.CategoryFlat;

import java.lang.invoke.CallSite;
import java.util.List;

public interface CategoryService {
    int addCategory(List<Category> categories);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    Category inquireCategoryById(Integer id);

    List<Category> inquireCategoryByParentId(Integer parentId);

    Category inquireCategoryByLatinNameAndParentId(Integer parentId, String latinName);

    List<CategoryFlat> inquireAllCategoriesByLevel(Integer level, Integer start, Integer pageSize);

    CategoryCount inquireCategoryCountById(Integer id);

    Integer inquirePageCount(Integer level);
}
