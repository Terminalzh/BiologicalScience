package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping
    public ResponseEntity<Response> addUser(@RequestBody User user, HttpSession session) {
        if (userService.addUser(user) == 1) {
            user = userService.inquireUserByEmailOrPhone(user);
            user.setIsAdmin(user.getEmail().equals(user.getPhone()));
            if (session.getAttribute("user") == null)
                session.setAttribute("user", user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, user));
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Response> login(@RequestBody Map<String, String> map, HttpSession session) {
        User account = new User();
        account.setEmail(map.get("username"));
        account.setPhone(map.get("username"));
        account.setPassword(map.get("password"));
        User user = userService.inquireUserByEmailOrPhone(account);
        if (user != null) {
            user.setIsAdmin(user.getEmail().equals(user.getPhone()));
            session.setAttribute("user", user);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, user));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable("id") Integer id) {
        if (userService.deleteUserById(id) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity<Response> logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
        user.setId(id);
        if (userService.updateUserById(user) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> inquireUserById(@PathVariable("id") Integer id) {
        User user = userService.inquireUserById(id);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, user));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Response> inquireAllUser(Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> users = userService.inquireAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        if (users.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, userPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/me")
    public ResponseEntity<Response> getCurrentUser(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, user));
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(HttpStatus.UNAUTHORIZED));
    }
}
