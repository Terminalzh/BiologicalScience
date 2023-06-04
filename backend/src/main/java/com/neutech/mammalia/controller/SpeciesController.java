package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Resource
    private SpeciesService speciesService;

    @PostMapping
    public Map<String, Object> addSpecies(@RequestBody Species species) {
        Map<String, Object> map = new HashMap<>();
        int i = speciesService.addSpecies(species);
        if (i == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", "添加成功");
            map.put("data", species);
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "添加失败");
        }
        return map;
    }
}
