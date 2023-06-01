package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoService {
    int addPhoto(@Param("photo") Photo photo);

    int deletePhotoById(@Param("id") Integer id);

    int updatePhotoById(@Param("photo") Photo photo);

    Photo inquirePhotoById(@Param("id") Integer id);

    List<Photo> inquireAllPhotos();

}
