package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Works;

import java.util.List;

public interface WorksService {
    int addWorks(Works works);

    int deleteWorksById(Integer id);

    int deleteWorksByUserId(Integer userId);

    int updateWorksById(Works works);

    List<Works> inquireWorksById(Integer id);

    List<Works> inquireAllWorksByUserId(Integer userId);

    List<Works> inquireAllWorksByPhotoWallId(Integer photoWallId);

    List<Works> inquireAllWorks();
}
