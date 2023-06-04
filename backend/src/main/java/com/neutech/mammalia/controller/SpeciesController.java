package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Resource
    private SpeciesService speciesService;

    @PostMapping
    public Map<String, Object> addSpecies(@RequestBody Species species) {
        Map<String, Object> map = new HashMap<>();
        if (speciesService.addSpecies(species) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", "添加成功");
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "添加失败");
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteSpeciesById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (speciesService.deleteSpeciesById(id) == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateSpeciesById(@PathVariable Integer id, @RequestBody Species species) {
        Map<String, Object> map = new HashMap<>();
        species.setId(id);
        if (speciesService.updateSpeciesById(species) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }


    @GetMapping(value = "/{id}")
    public Map<String, Object> inquireSpeciesById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Species species = speciesService.inquireSpeciesById(id);
        if (species != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", species);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllUser() {
        Map<String, Object> map = new HashMap<>();
        List<Species> species = speciesService.inquireAllSpecies();
        if (species.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.getReasonPhrase());
            map.put("data", species);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }
}
