package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.mapper.WorksMapper;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksServiceImpl implements WorksService {
    @Resource
    private WorksMapper worksMapper;

    @Override
    public int addWorks(Works works) {
        return 0;
    }

    @Override
    public int deleteWorksById(Integer id) {
        return 0;
    }

    @Override
    public int deleteWorksByUserId(Integer userId) {
        return 0;
    }

    @Override
    public int updateWorksById(Works works) {
        return 0;
    }

    @Override
    public List<Works> inquireWorksById(Integer id) {
        return null;
    }

    @Override
    public List<Works> inquireAllWorksByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<Works> inquireAllWorksByPhotoWallId(Integer photoWallId) {
        return null;
    }

    @Override
    public List<Works> inquireAllWorks() {
        return null;
    }
}
