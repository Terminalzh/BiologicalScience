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
        return 0;
    }

    @Override
    public int deletePhotoById(Integer id) {
        return 0;
    }

    @Override
    public int updatePhotoById(Photo photo) {
        return 0;
    }

    @Override
    public Photo inquirePhotoById(Integer id) {
        return null;
    }

    @Override
    public List<Photo> inquireAllPhotos() {
        return null;
    }
}
