package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping
    public Response addUser(@RequestBody User user, HttpSession session) {
        Response response = new Response();
        if (userService.addUser(user) == 1) {
            session.setAttribute("user", user);
            user = userService.inquireUserByEmailOrPhone(user);
            user.setIsAdmin(user.getEmail().equals(user.getPhone()));
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
            response.setData(user);
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @PostMapping(value = "/login")
    public Response login(@RequestBody User account, HttpSession session) {
        Response response = new Response();
        User user = userService.inquireUserByEmailOrPhone(account);
        if (user != null) {
            user = userService.inquireUserByEmailOrPhone(user);
            user.setIsAdmin(user.getEmail().equals(user.getPhone()));
            session.setAttribute("user", user);
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(user);
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("邮箱或手机号或密码错误");
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteUserById(@PathVariable("id") Integer id) {
        Response response = new Response();
        if (userService.deleteUserById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/logout")
    public Response logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        Response response = new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
        Response response = new Response();
        user.setId(id);
        if (userService.updateUserById(user) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/{id}")
    public Response inquireUserById(@PathVariable("id") Integer id) {
        Response response = new Response();
        User user = userService.inquireUserById(id);
        if (user != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(user);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllUser(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> users = userService.inquireAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        if (users.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(userPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping("/me")
    public Response getCurrentUser(HttpServletResponse httpServletResponse, HttpSession session) {
        Response response = new Response();
        Object user = session.getAttribute("user");
        if (user != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(user);
        } else {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return response;
    }
}
