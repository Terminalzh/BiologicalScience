package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoService {
    int addPhoto(Photo photo);

    int deletePhotoById(Integer id);
    int deletePhotoBySpeciesId(Integer speciesId);

    int updatePhotoById(Photo photo);

    Photo inquirePhotoById(Integer id);

    List<Photo> inquireAllPhotos();

}
