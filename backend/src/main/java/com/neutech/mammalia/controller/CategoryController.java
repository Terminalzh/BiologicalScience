package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.*;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable("id") Integer id) {
        int i = categoryService.deleteCategoryById(id);
        if (i >= 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "删除成功, 已删除" + i + "条数据"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Response> updateCategoryNameById(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setId(id);
        if (categoryService.updateCategoryById(category) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> inquireCategoryById(@PathVariable("id") Integer id) {
        List<Category> categories = categoryService.inquireCategoryByParentId(id);
        if (categories.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, categories));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/count/{id}")
    public ResponseEntity<Response> inquireCategoryCountById(@PathVariable("id") Integer id) {
        CategoryCount categoryCount = categoryService.inquireCategoryCountById(id);
        if (categoryCount != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, categoryCount));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Response> inquireAllCategories(Integer level) {
        List<CategoryFlat> list = categoryService.inquireAllCategoriesByLevel(level, 0, Integer.MAX_VALUE);
        if (list.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, list));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Response> inquireAllCategoriesByPage(Integer level, Page<Integer> page) {
        List<CategoryFlat> list = categoryService.inquireAllCategoriesByLevel(level, (page.getPageNum() - 1) * page.getPageSize(), page.getPageSize());
        Integer pageCount = categoryService.inquirePageCount(level);
        PageInfo<CategoryFlat> pageInfo = new PageInfo<>();
        pageInfo.setTotal(list.size());
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setSize(list.size());
        pageInfo.setPages((int) Math.ceil((double) pageCount / page.getPageSize()));
        pageInfo.setList(list);
        if (pageInfo.getSize() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, pageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/info")
    public ResponseEntity<Response> inquireCategoryInfoById(@PathVariable Integer id) {
        Category category = categoryService.inquireCategoryById(id);
        if (category != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, category));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }
}
