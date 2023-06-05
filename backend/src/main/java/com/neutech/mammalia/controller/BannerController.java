package com.neutech.mammalia.controller;

import com.github.pagehelper.PageHelper;
import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.service.BannerService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @PostMapping
    public Map<String, Object> addBanner(@RequestBody Integer speciesImageId) {
        Map<String, Object> map = new HashMap<>();
        if (bannerService.addBanner(speciesImageId) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", HttpStatus.CREATED.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteBannerById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (bannerService.deleteBannerById(id) == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.value());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateBannerById(@PathVariable Integer id, @RequestBody Integer speciesImageId) {
        Map<String, Object> map = new HashMap<>();
        if (bannerService.updateBannerById(id, speciesImageId) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> inquireBannerById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Banner banner = bannerService.inquireBannerById(id);
        if (banner != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", banner);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllBanner(@RequestBody Integer current, @RequestBody Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(current, pageSize);
        List<Banner> banners = bannerService.inquireAllBanner();
        if (banners.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", banners);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }
}
