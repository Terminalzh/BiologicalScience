package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
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
    public Response addSpeciesImageService(@RequestBody SpeciesImage speciesImage) {
        Response response = new Response();
        if (speciesImageService.addSpeciesImage(speciesImage) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteSpeciesImageById(@PathVariable Integer id) {
        Response response = new Response();
        if (speciesImageService.deleteSpeciesImageById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateSpeciesImageById(@PathVariable Integer id, @RequestBody SpeciesImage speciesImage) {
        Response response = new Response();
        speciesImage.setId(id);
        if (speciesImageService.updateSpeciesImageById(speciesImage) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response inquireSpeciesImageById(@PathVariable Integer id) {
        Response response = new Response();
        SpeciesImage speciesImage = speciesImageService.inquireSpeciesImageById(id);
        if (speciesImage != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(speciesImage);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllSpeciesImage(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<SpeciesImage> speciesImage = speciesImageService.inquireAllSpeciesImages();
        PageInfo<SpeciesImage> speciesImagePageInfo = new PageInfo<>();
        if (speciesImage.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(speciesImagePageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }
}
