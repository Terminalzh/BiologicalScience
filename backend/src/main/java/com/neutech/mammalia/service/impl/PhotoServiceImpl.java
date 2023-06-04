package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.mapper.PhotoMapper;
import com.neutech.mammalia.service.PhotoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoMapper photoMapper;

    @Override
    public int addPhoto(Photo photo) {
        return photoMapper.addPhoto(photo);
    }

    @Override
    public int deletePhotoById(Integer id) {
        return photoMapper.deletePhotoById(id);
    }

    @Override
    public int updatePhotoById(Photo photo) {
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
