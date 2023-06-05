package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.SpeciesImage;
import com.neutech.mammalia.mapper.SpeciesImageMapper;
import com.neutech.mammalia.service.BannerService;
import com.neutech.mammalia.service.SpeciesImageService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpeciesImageServiceImpl implements SpeciesImageService {
    @Resource
    private SpeciesImageMapper speciesImageMapper;
    @Lazy
    @Resource
    private BannerService bannerService;

    @Override
    public int addSpeciesImage(SpeciesImage speciesImage) {
        return speciesImageMapper.addSpeciesImage(speciesImage);
    }

    @Override
    public int deleteSpeciesImageById(Integer id) {
        bannerService.deleteBannerBySpeciesImageId(id);
        return speciesImageMapper.deleteSpeciesImageById(id);
    }

    @Override
    public int deleteSpeciesImageBySpeciesId(Integer speciesId) {
        return speciesImageMapper.deleteSpeciesImageBySpeciesId(speciesId);
    }

    @Override
    public int updateSpeciesImageById(SpeciesImage speciesImage) {
        speciesImage.setUpdateTime(new Date());
        return speciesImageMapper.updateSpeciesImageById(speciesImage);
    }

    @Override
    public SpeciesImage inquireSpeciesImageById(Integer id) {
        return speciesImageMapper.inquireSpeciesImageById(id);
    }

    @Override
    public List<SpeciesImage> inquireAllSpeciesImages() {
        return speciesImageMapper.inquireAllSpeciesImages();
    }
}
