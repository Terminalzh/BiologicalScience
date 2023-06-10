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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/works")
public class WorksController {
    @Resource
    private WorksService worksService;

    @PostMapping
    public ResponseEntity<Response> addWorks(HttpSession session, @RequestBody Map<String, Object> map) {
        User user = (User) session.getAttribute("user");
        Works works = new Works();
        works.setImageUrl((String) map.get("imageUrl"));
        works.setIsPublic((Boolean) map.get("isPublic"));
        if (worksService.addWorks(works, user.getId(), (Integer) map.get("speciesId")) == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, "上传成功"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST, "上传失败"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteWorksById(@PathVariable("id") Integer id) {
        if (worksService.deleteWorksById(id) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "删除成功"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND, "未找到该条记录,可能已被删除或不存在"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateWorksById(
            @PathVariable("id") Integer id, HttpSession session, @RequestBody Map<String, Object> map) {
        Works works = new Works();
        works.setImageUrl((String) map.get("imageUrl"));
        works.setIsPublic((Boolean) map.get("isPublic"));
        works.setViewCount((Integer) map.get("viewCount"));
        works.setLikeCount((Integer) map.get("likeCount"));
        works.setCommentCount((Integer) map.get("commentCount"));
        works.setId(id);
        if (worksService.updateWorksById(works, (Integer) map.get("speciesId")) >= 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "修改成功"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST, "修改失败,请检查您的数据"));

    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Response> inquireWorksByUserId(@PathVariable Integer userId) {

        List<Works> works = worksService.inquireAllWorksByUserId(userId);
        if (works.size() >= 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, works));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND, "该用户不存在或该用户没有作品"));
    }

    @GetMapping
    public ResponseEntity<Response> inquireAllWorks(Page<Integer> page, HttpSession session) {
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
        if (works.size() >= 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, worksPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND, "没有作品"));
    }

    @GetMapping(value = "/recommends")
    public ResponseEntity<Response> inquireSomeWorks() {
        Set<Works> works = worksService.inquireSomeWorks();
        if (works.size() >= 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, works));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

}
