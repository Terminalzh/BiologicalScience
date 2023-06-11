package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.CategoryFlat;

import java.util.List;

public interface CategoryService {
    int addCategory(Category category);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    Category inquireCategoryById(Integer id);

    List<Category> inquireCategoryByParentId(Integer parentId);

    Category inquireCategoryByLatinNameAndParentId(Integer parentId, String latinName);

    Category inquireCategoryByName(String latinName, String cName);

    List<CategoryFlat> inquireAllCategoriesByLevel(Integer level, Integer start, Integer pageSize);

    CategoryCount inquireCategoryCountById(Integer id);

    Integer inquirePageCount(Integer level);
}
