package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.bean.SpeciesImage;
import com.neutech.mammalia.mapper.BannerMapper;
import com.neutech.mammalia.service.BannerService;
import com.neutech.mammalia.service.SpeciesImageService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Lazy
    @Resource
    private SpeciesImageService speciesImageService;

    @Override
    public int addBanner(Integer speciesImageId) {
        SpeciesImage speciesImage = speciesImageService.inquireSpeciesImageById(speciesImageId);
        Banner banner = new Banner();
        banner.setSpeciesImage(speciesImage);
        Date date = new Date();
        banner.setCreateTime(date);
        banner.setUpdateTime(date);
        return bannerMapper.addBanner(banner);
    }

    @Override
    public int deleteBannerById(Integer id) {
        return bannerMapper.deleteBannerById(id);
    }

    @Override
    public int deleteBannerBySpeciesImageId(int speciesImageId) {
        return bannerMapper.deleteBannerBySpeciesImageId(speciesImageId);
    }

    @Override
    public int updateBannerById(Integer id, Integer speciesImageId) {
        Banner banner = new Banner();
        banner.setId(id);
        SpeciesImage speciesImage = speciesImageService.inquireSpeciesImageById(speciesImageId);
        banner.setSpeciesImage(speciesImage);
        banner.setUpdateTime(new Date());
        return bannerMapper.updateBannerById(banner);
    }

    @Override
    public Banner inquireBannerById(Integer id) {
        return bannerMapper.inquireBannerById(id);
    }

    @Override
    public List<Banner> inquireAllBanner() {
        return bannerMapper.inquireAllBanner();
    }
}
