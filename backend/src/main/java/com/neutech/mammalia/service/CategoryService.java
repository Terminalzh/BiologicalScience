package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    int addCategory(List<Category> categories);

    int deleteCategoryById(Integer id);

    int updateCategoryById(Category category);

    Category inquireCategoryById(Integer id);

    List<Category> inquireCategoryByParentId(Integer parentId);

    List<Category> inquireAllCategory();
}
