package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.service.BannerService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @PostMapping
    public Response addBanner(@RequestBody Map<String, Integer> param) {
        Response response = new Response();
        Integer speciesId = param.get("speciesId");
        if (bannerService.addBanner(speciesId) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
            response.setData(bannerService.inquireBannerBySpeciesId(speciesId));
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteBannerById(@PathVariable Integer id) {
        Response response = new Response();
        if (bannerService.deleteBannerById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateBannerById(@PathVariable Integer id, @RequestBody Map<String, Integer> param) {
        Response response = new Response();
        Integer speciesId = param.get("speciesId");
        if (bannerService.updateBannerById(id, speciesId) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response inquireBannerBySpeciesId(@PathVariable Integer id) {
        Response response = new Response();
        Banner banner = bannerService.inquireBannerBySpeciesId(id);
        if (banner != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(banner);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllBanner(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Banner> list = bannerService.inquireAllBanner();
        PageInfo<Banner> banners = new PageInfo<>(list);
        if (list.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(banners);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }
}
