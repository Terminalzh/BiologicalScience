package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.*;
import com.neutech.mammalia.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public Response addCategory(@RequestBody List<Category> categories) {
        Response response = new Response();
        if (categoryService.addCategory(categories) >= 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteCategoryById(@PathVariable("id") Integer id) {
        Response response = new Response();
        int i = categoryService.deleteCategoryById(id);
        if (i >= 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage("删除成功, 已删除" + i + "条数据");
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Response updateCategoryNameById(@PathVariable("id") Integer id, @RequestBody Category category) {
        Response response = new Response();
        category.setId(id);
        if (categoryService.updateCategoryById(category) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/{id}")
    public Response inquireCategoryById(@PathVariable("id") Integer id) {
        Response response = new Response();
        List<Category> categories = categoryService.inquireCategoryByParentId(id);
        if (categories.size() != 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(categories);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/count/{id}")
    public Response inquireCategoryCountById(@PathVariable("id") Integer id) {
        Response response = new Response();
        CategoryCount categoryCount = categoryService.inquireCategoryCountById(id);
        if (categoryCount != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(categoryCount);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllCategories(Integer level, Page<Integer> page) {
        Response response = new Response();
        if (page != null)
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        else {
            List<CategoryFlat> list = categoryService.inquireAllCategoriesByLevel(level);
            if (list.size() > 0) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setData(list);
            }
        }
        return response;
    }

    @GetMapping(value = "/page")
    public Response inquireAllCategoriesByPage(Integer level, Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CategoryFlat> list = categoryService.inquireAllCategoriesByLevel(level);
        PageInfo<CategoryFlat> pageInfo = new PageInfo<>(list);
        if (pageInfo.getSize() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(pageInfo);
        }
        return response;
    }
}
