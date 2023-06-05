package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/works")
public class WorksController {
    @Resource
    private WorksService worksService;

    @PostMapping
    public Map<String, Object> addWorks(@RequestBody Works works) {
        Map<String, Object> map = new HashMap<>();
        if (worksService.addWorks(works) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", "上传成功");
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "上传失败");
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteWorksById(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (worksService.deleteWorksById(id) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "删除成功");
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "未找到该条记录,可能已被删除或不存在");
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateWorksById(@PathVariable("id") Integer id, @RequestBody Works works) {
        Map<String, Object> map = new HashMap<>();
        works.setId(id);
        if (worksService.updateWorksById(works) >= 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "修改成功");
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "修改失败,请检查您的数据");
        }
        return map;
    }

    @GetMapping(value = "/{userId}")
    public Map<String, Object> inquireWorksByUserId(@PathVariable Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<Works> works = worksService.inquireAllWorksByUserId(userId);
        if (works.size() >= 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "success");
            map.put("data", works);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "该用户不存在或该用户没有作品");
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllWorks() {
        Map<String, Object> map = new HashMap<>();
        List<Works> works = worksService.inquireAllWorks();
        if (works.size() >= 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", "success");
            map.put("data", works);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "没有作品");
        }
        return map;
    }
}
