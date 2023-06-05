package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Works;

import java.util.List;

public interface WorksService {
    int addWorks(Works works, Integer userId, Integer speciesId);

    int deleteWorksById(Integer id);

    int deleteWorksByUserId(Integer userId);

    int deleteWorksBySpeciesId(Integer speciesId);

    int updateWorksById(Works works, Integer speciesId);

    Works inquireWorksById(Integer id);

    List<Works> inquireAllWorksByUserId(Integer userId);

    List<Works> inquireAllWorks();
}
