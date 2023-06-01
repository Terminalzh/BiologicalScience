package com.neutech.mammalia.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @PutMapping(value = "/addUser")
    public Map<String, Object> addUser() {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
