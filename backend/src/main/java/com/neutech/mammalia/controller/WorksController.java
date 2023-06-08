package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
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
    public Response addWorks(
            @RequestBody Works works,
            @RequestBody Integer userId,
            @RequestBody Integer speciesId
    ) {
        Response response = new Response();
        if (worksService.addWorks(works, userId, speciesId) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("上传成功");
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("上传失败");
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteWorksById(@PathVariable("id") Integer id) {
        Response response = new Response();
        if (worksService.deleteWorksById(id) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("删除成功");
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("未找到该条记录,可能已被删除或不存在");
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateWorksById(
            @PathVariable("id") Integer id,
            @RequestBody Works works,
            @RequestBody Integer speciesId
    ) {
        Response response = new Response();
        works.setId(id);
        if (worksService.updateWorksById(works, speciesId) >= 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("修改成功");
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("修改失败,请检查您的数据");
        }
        return response;
    }

    @GetMapping(value = "/{userId}")
    public Response inquireWorksByUserId(@PathVariable Integer userId) {
        Response response = new Response();
        List<Works> works = worksService.inquireAllWorksByUserId(userId);
        if (works.size() >= 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(works);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("该用户不存在或该用户没有作品");
        }
        return response;
    }

    @GetMapping
    public Response inquireAllWorks(Page<Integer> page, HttpSession session) {
        Response response = new Response();
        User user = (User) session.getAttribute("user");
        List<Works> works;
        PageInfo<Works> worksPageInfo;
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        if (user.getIsAdmin())
            works = worksService.inquireAllWorks();
        else
            works = worksService.inquireAllWorksByUserId(user.getId());
        for (Works work : works) {
            work.setUser(user);
        }
        worksPageInfo = new PageInfo<>(works);
        if (works.size() >= 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(worksPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("没有作品");
        }
        return response;
    }
}
