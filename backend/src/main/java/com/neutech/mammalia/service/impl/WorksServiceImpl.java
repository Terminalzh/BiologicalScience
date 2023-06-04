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
        return worksMapper.addWorks(works);
    }

    @Override
    public int deleteWorksById(Integer id) {
        return worksMapper.deleteWorksById(id);
    }

    @Override
    public int deleteWorksByUserId(Integer userId) {
        return worksMapper.deleteWorksByUserId(userId);
    }

    @Override
    public int deleteWorksBySpeciesId(Integer speciesId) {
        return worksMapper.deleteWorksBySpeciesId(speciesId);
    }

    @Override
    public int updateWorksById(Works works) {
        return worksMapper.updateWorksById(works);
    }

    @Override
    public List<Works> inquireWorksById(Integer id) {
        return worksMapper.inquireWorksById(id);
    }

    @Override
    public List<Works> inquireAllWorksByUserId(Integer userId) {
        return worksMapper.inquireWorksIdByUserId(userId);
    }

    @Override
    public List<Works> inquireAllWorks() {
        return worksMapper.inquireAllWorks();
    }
}
