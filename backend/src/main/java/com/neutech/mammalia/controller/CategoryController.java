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
        int result = categoryService.addCategory(categories);
        if (result >= 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "Add Success");
            map.put("data", categories);
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "Add Fail");
        }
        return map;
    }

    @GetMapping("/{parentId}")
    public Map<String, Object> inquireCategoryById(@PathVariable("parentId") Integer parentId) {
        Map<String, Object> map = new HashMap<>();
        List<Category> categories = categoryService.inquireCategoryByParentId(parentId);
        if (categories.size() != 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "Success");
            map.put("data", categories);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "Not Found");
        }
        return map;
    }
}
