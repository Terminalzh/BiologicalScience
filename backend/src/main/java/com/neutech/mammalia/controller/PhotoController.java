package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.service.PhotoService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {
    @Resource
    private PhotoService photoService;

    @PostMapping
    public Map<String, Object> addPhoto(@RequestBody Photo photo) {
        Map<String, Object> map = new HashMap<>();
        int i = photoService.addPhoto(photo);
        if (i == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", HttpStatus.CREATED.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deletePhotoById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        int i = photoService.deletePhotoById(id);
        if (i == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.value());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updatePhotoById(@PathVariable Integer id, @RequestBody Photo photo) {
        Map<String, Object> map = new HashMap<>();
        photo.setId(id);
        int i = photoService.updatePhotoById(photo);
        if (i == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> inquirePhotoById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Photo photo = photoService.inquirePhotoById(id);
        if (photo != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", photo);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllPhoto() {
        Map<String, Object> map = new HashMap<>();
        List<Photo> photos = photoService.inquireAllPhotos();
        if (photos.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", photos);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

}
