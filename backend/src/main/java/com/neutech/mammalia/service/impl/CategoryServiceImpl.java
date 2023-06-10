package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.CategoryFlat;
import com.neutech.mammalia.mapper.CategoryMapper;
import com.neutech.mammalia.service.CategoryCountService;
import com.neutech.mammalia.service.CategoryService;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SpeciesService speciesService;
    @Lazy
    @Resource
    private CategoryCountService categoryCountService;

    @Override
    public int addCategory(List<Category> categories) {
        int count = 0;
        //获取最高级
        Category parent = categoryMapper.inquireCategoryById(1);
        int i = 0;
        for (Category category : categories) {
            i++;
            //先设置父id
            category.setParentId(parent.getId());
            //通过父id和拉丁文名判断当前分类是否存在
            Category categoryByName = categoryMapper.inquireCategoryByLatinNameAndParentId(category.getParentId(), category.getLatinName());
            //不存在就新增
            if (categoryByName == null) {
                count += categoryMapper.addCategory(category);
            }
            //更新父级
            parent = categoryMapper.inquireCategoryByLatinNameAndParentId(category.getParentId(), category.getLatinName());
        }
        //在所有分类增加后再增加数量
        categoryCountService.addCategoryCount(categories);
        return count;
    }

    @Override
    public int deleteCategoryById(Integer id) {
        int count = 0;
        //先获取所有的子级
        List<Category> categories = categoryMapper.inquireCategoryByParentId(id);
        //如果没有子级,就开始删除
        if (categories.size() == 0) {
            count += speciesService.deleteSpeciesByGenusId(id);
            count += categoryCountService.deleteCategoryCountById(id);
            count += categoryMapper.deleteCategoryById(id);
            return count;
        } else {
            //如果有子级,就遍历每个子级,然后递归调用
            for (Category category : categories) {
                count += deleteCategoryById(category.getId());
            }
            //删除所有子级后,删除当前分类
            count += speciesService.deleteSpeciesByGenusId(id);
            count += categoryCountService.deleteCategoryCountById(id);
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
        Category category = categoryMapper.inquireCategoryById(id);
        String inheritance = categoryCountService.inquireCategorizedInheritanceById(id);
        category.setLevel(inheritance.split("\\.").length);
        return category;
    }

    @Override
    public List<Category> inquireCategoryByParentId(Integer parentId) {
        return categoryMapper.inquireCategoryByParentId(parentId);
    }

    @Override
    public Category inquireCategoryByLatinNameAndParentId(Integer parentId, String latinName) {
        return categoryMapper.inquireCategoryByLatinNameAndParentId(parentId, latinName);
    }

    @Override
    public List<CategoryFlat> inquireAllCategoriesByLevel(Integer level, Integer start, Integer pageSize) {
        String expression = "^(?:(?!" + "\\d+\\.".repeat(level) + "\\d+).)*$";
        List<CategoryFlat> list = new ArrayList<>();
        List<CategoryCount> categoryCounts = categoryCountService.inquireAllCategories(expression, start, pageSize);
        for (CategoryCount categoryCount : categoryCounts) {
            Category category = categoryMapper.inquireCategoryById(categoryCount.getId());
            CategoryFlat categoryFlat = new CategoryFlat();
            categoryFlat.setId(category.getId().toString());
            if (category.getParentId() != null)
                categoryFlat.setParent(category.getParentId().toString());
            if (category.getCName() != null)
                categoryFlat.setName(category.getLatinName() + "(" + category.getCName() + ")");
            else categoryFlat.setName(category.getLatinName());
            int length = categoryCount.getCategorizedInheritance().split("\\.").length;
            switch (length) {
                case 1 -> categoryFlat.setValue(categoryCount.getSubClass());
                case 2 -> categoryFlat.setValue(categoryCount.getOrderCount());
                case 3 -> categoryFlat.setValue(categoryCount.getFamily());
                case 4 -> categoryFlat.setValue(categoryCount.getGenus());
                case 5 -> categoryFlat.setValue(categoryCount.getSpecies());
            }
            categoryFlat.setLevel(length);
            list.add(categoryFlat);
        }
        return list;
    }

    @Override
    public CategoryCount inquireCategoryCountById(Integer id) {
        return categoryCountService.inquireCategoryCount(id);
    }

    @Override
    public Integer inquirePageCount(Integer level) {
        String expression = "^(?:(?!" + "\\d+\\.".repeat(level) + "\\d+).)*$";
        return categoryCountService.inquirePageCount(expression);
    }
}
