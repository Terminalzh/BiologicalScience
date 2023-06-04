package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.SpeciesImage;
import com.neutech.mammalia.mapper.SpeciesImageMapper;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.SpeciesImageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesImageServiceImpl implements SpeciesImageService {
    @Resource
    private SpeciesImageMapper speciesImageMapper;

    @Override
    public int addSpeciesImage(SpeciesImage speciesImage) {
        return 0;
    }

    @Override
    public int deleteSpeciesImageById(Integer id) {
        return 0;
    }

    @Override
    public int deleteSpeciesImageBySpeciesId(Integer speciesId) {
        return speciesImageMapper.deleteSpeciesImageBySpeciesId(speciesId);
    }

    @Override
    public int updateSpeciesImageById(SpeciesImage speciesImage) {
        return 0;
    }

    @Override
    public SpeciesImage inquireSpeciesImageById(Integer id) {
        return null;
    }

    @Override
    public List<SpeciesImage> inquireAllSpeciesImages() {
        return null;
    }
}
