package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.bean.Report;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.service.PhotoService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> addPhoto(@RequestBody Map<String, Object> param) {
        Integer worksId = (Integer) param.get("speciesId");
        if (photoService.addPhoto(worksId) == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deletePhotoById(@PathVariable Integer id) {
        if (photoService.deletePhotoById(id) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updatePhotoById(@PathVariable Integer id, @RequestBody Photo photo) {
        photo.setId(id);
        if (photoService.updatePhotoById(photo) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> inquirePhotoById(@PathVariable Integer id) {
        Photo photo = photoService.inquirePhotoById(id);
        if (photo != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, photo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    public ResponseEntity<Response> inquireAllPhoto(Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Photo> photos = photoService.inquireAllPhotos();
        PageInfo<Photo> photoPageInfo = new PageInfo<>(photos);
        if (photos.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, photoPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));

    }
}
