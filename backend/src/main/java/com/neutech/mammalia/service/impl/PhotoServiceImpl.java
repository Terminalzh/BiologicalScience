package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.mapper.PhotoMapper;
import com.neutech.mammalia.service.PhotoService;
import com.neutech.mammalia.service.SpeciesService;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoMapper photoMapper;

    @Lazy
    @Resource
    private SpeciesService speciesService;

    @Override
    public int addPhoto(Integer speciesId) {
        Photo photo = new Photo();
        Date date = new Date();
        Species species = speciesService.inquireSpeciesById(speciesId);
        if (species.getBetterUrl() == null)
            return 0;
        photo.setSpecies(species);
        photo.setCreateTime(date);
        photo.setUpdateTime(date);
        return photoMapper.addPhoto(photo);
    }

    @Override
    public int deletePhotoById(Integer id) {
        return photoMapper.deletePhotoById(id);
    }

    @Override
    public int deletePhotoBySpeciesId(Integer speciesId) {
        return photoMapper.deletePhotoBySpeciesId(speciesId);
    }

    @Override
    public int updatePhotoById(Photo photo) {
        photo.setUpdateTime(new Date());
        return photoMapper.updatePhotoById(photo);
    }

    @Override
    public Photo inquirePhotoById(Integer id) {
        return photoMapper.inquirePhotoById(id);
    }

    @Override
    public List<Photo> inquireAllPhotos() {
        return photoMapper.inquireAllPhotos();
    }
}
