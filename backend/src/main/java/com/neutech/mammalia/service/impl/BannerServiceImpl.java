package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.BannerMapper;
import com.neutech.mammalia.service.BannerService;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private SpeciesService speciesService;

    @Override
    public int addBanner(Integer speciesId) {
        Banner banner = new Banner();
        Species species = speciesService.inquireSpeciesById(speciesId);
        banner.setSpecies(species);
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
    public int deleteBannerBySpeciesId(int speciesId) {
        return bannerMapper.deleteBannerBySpeciesImageId(speciesId);
    }

    @Override
    public int updateBannerById(Integer oldSpeciesId, Integer newSpeciesId) {
        return bannerMapper.updateBannerById(oldSpeciesId, newSpeciesId);
    }

    @Override
    public Banner inquireBannerById(Integer id) {
        return bannerMapper.inquireBannerById(id);
    }

    @Override
    public List<Banner> inquireBannerBySpeciesId(Integer speciesId) {
        return bannerMapper.inquireBannerBySpeciesId(speciesId);
    }

    @Override
    public List<Banner> inquireAllBanner() {
        return bannerMapper.inquireAllBanner();
    }
}
