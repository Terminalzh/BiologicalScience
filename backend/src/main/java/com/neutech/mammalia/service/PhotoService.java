package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PhotoService {
    int addPhoto(Integer worksId, Boolean isPublic);

    int deletePhotoById(Integer id);

    int updatePhotoById(Photo photo);

    Photo inquirePhotoById(Integer id);

    List<Photo> inquireAllPhotos();

}
