package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.SpeciesImage;
import com.neutech.mammalia.service.SpeciesImageService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/speciesImage")
public class SpeciesImageController {
    @Resource
    private SpeciesImageService speciesImageService;

    @PostMapping
    public Map<String, Object> addSpeciesImageService(@RequestBody SpeciesImage speciesImage) {
        Map<String, Object> map = new HashMap<>();
        if (speciesImageService.addSpeciesImage(speciesImage) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", HttpStatus.CREATED.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteSpeciesImageById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (speciesImageService.deleteSpeciesImageById(id) == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.value());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateSpeciesImageById(@PathVariable Integer id, @RequestBody SpeciesImage speciesImage) {
        Map<String, Object> map = new HashMap<>();
        speciesImage.setId(id);
        if (speciesImageService.updateSpeciesImageById(speciesImage) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> inquireSpeciesImageById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        SpeciesImage speciesImage = speciesImageService.inquireSpeciesImageById(id);
        if (speciesImage != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", speciesImage);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllSpeciesImage() {
        Map<String, Object> map = new HashMap<>();
        List<SpeciesImage> speciesImage = speciesImageService.inquireAllSpeciesImages();
        if (speciesImage.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", speciesImage);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }
}
