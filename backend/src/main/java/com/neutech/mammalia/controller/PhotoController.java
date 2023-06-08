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
    public Response addPhoto(@RequestBody Map<String, Object> param) {
        Response response = new Response();
        Integer worksId = (Integer) param.get("speciesId");
        if (photoService.addPhoto(worksId) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deletePhotoById(@PathVariable Integer id) {
        Response response = new Response();
        if (photoService.deletePhotoById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updatePhotoById(@PathVariable Integer id, @RequestBody Photo photo) {
        Response response = new Response();
        photo.setId(id);
        if (photoService.updatePhotoById(photo) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response inquirePhotoById(@PathVariable Integer id) {
        Response response = new Response();
        Photo photo = photoService.inquirePhotoById(id);
        if (photo != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(photo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllPhoto(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Photo> photos = photoService.inquireAllPhotos();
        PageInfo<Photo> photoPageInfo = new PageInfo<>(photos);
        if (photos.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(photoPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

}
