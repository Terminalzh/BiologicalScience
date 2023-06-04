package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public Map<String, Object> addCategory(@RequestBody List<Category> categories) {
        Map<String, Object> map = new HashMap<>();
        if (categoryService.addCategory(categories) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", HttpStatus.CREATED.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteCategoryById(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        int i = categoryService.deleteCategoryById(id);
        if (i >= 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", "删除成功, 已删除" + i + "条数据");
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateCategoryNameById(@PathVariable("id") Integer id, @RequestBody Category category) {
        Map<String, Object> map = new HashMap<>();
        category.setId(id);
        if (categoryService.updateCategoryById(category) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping(value = "/{id}")
    public Map<String, Object> inquireCategoryById(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        List<Category> categories = categoryService.inquireCategoryByParentId(id);
        if (categories.size() != 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", categories);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }
}
