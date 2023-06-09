package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.service.BannerService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @PostMapping
    public ResponseEntity<Response> addBanner(@RequestBody Map<String, Integer> param) {
        Integer speciesId = param.get("speciesId");
        if (bannerService.addBanner(speciesId) == 1) {
            Banner banner = bannerService.inquireBannerBySpeciesId(speciesId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, banner));
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{speciesId}")
    public ResponseEntity<Response> deleteBannerById(@PathVariable Integer speciesId) {
        if (bannerService.deleteBannerBySpeciesId(speciesId) == 1)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(HttpStatus.NO_CONTENT));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{oldSpeciesId}")
    public ResponseEntity<Response> updateBannerById(@PathVariable Integer oldSpeciesId, @RequestBody Map<String, Integer> param) {
        Integer speciesId = param.get("newSpeciesId");
        if (bannerService.updateBannerById(oldSpeciesId, speciesId) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{speciesId}")
    public ResponseEntity<Response> inquireBannerBySpeciesId(@PathVariable Integer speciesId) {
        Banner banner = bannerService.inquireBannerBySpeciesId(speciesId);
        if (banner != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, banner));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Response> inquireAllBannerByPage(Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Banner> list = bannerService.inquireAllBanner();
        PageInfo<Banner> banners = new PageInfo<>(list);
        if (list.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, banners));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Response> inquireAllBanner() {
        List<Banner> list = bannerService.inquireAllBanner();
        if (list.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, list));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }
}
