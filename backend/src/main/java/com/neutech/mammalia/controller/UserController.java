package com.neutech.mammalia.controller;

import com.github.pagehelper.PageHelper;
import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping
    public Map<String, Object> addUser(@RequestBody User user, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (userService.addUser(user) == 1) {
            session.setAttribute("user", user);
            map.put("code", HttpStatus.CREATED.value());
            if (user.getEmail().equals(user.getPhone()))
                map.put("message", "admin");
            else
                map.put("message", "user");
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @PostMapping(value = "/login")
    public Map<String, Object> login(@RequestBody User account, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.inquireUserCountByEmailOrPhone(account);
        if (user != null) {
            session.setAttribute("user", user);
            map.put("code", HttpStatus.OK.value());
            if (user.getEmail().equals(user.getPhone()))
                map.put("message", "admin");
            else
                map.put("message", "user");
            map.put("data", user);
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "邮箱或手机号或密码错误");
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteUserById(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (userService.deleteUserById(id) == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK.value());
        map.put("message", HttpStatus.OK.getReasonPhrase());
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        user.setId(id);
        if (userService.updateUserById(user) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @GetMapping(value = "/{id}")
    public Map<String, Object> inquireUserById(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.inquireUserById(id);
        if (user != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", user);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllUser(
            @RequestParam(value = "current", required = false) Integer current,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(current, pageSize);
        List<User> users = userService.inquireAllUser();
        if (users.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", users);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Object user = session.getAttribute("user");
        if (user != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", user);
        } else {
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return map;
    }
}
