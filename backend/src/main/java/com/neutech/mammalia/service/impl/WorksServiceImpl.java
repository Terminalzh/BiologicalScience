package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.mapper.WorksMapper;
import com.neutech.mammalia.service.UserService;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorksServiceImpl implements WorksService {
    @Resource
    private WorksMapper worksMapper;
    @Lazy
    @Resource
    private UserService userService;
    @Lazy
    @Resource
    private SpeciesServiceImpl speciesService;

    @Override
    public int addWorks(Works works, Integer userId, Integer speciesId) {
        User user = userService.inquireUserById(userId);
        works.setUser(user);
        Species species = speciesService.inquireSpeciesById(speciesId);
        works.setSpecies(species);
        Date date = new Date();
        works.setCreateTime(date);
        works.setUpdateTime(date);
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
    public int updateWorksById(Works works, Integer speciesId) {
        Species species = speciesService.inquireSpeciesById(speciesId);
        works.setSpecies(species);
        works.setUpdateTime(new Date());
        return worksMapper.updateWorksById(works);
    }

    @Override
    public Works inquireWorksById(Integer id) {
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

    @Override
    public Set<Works> inquireSomeWorks() {
        List<Works> works = worksMapper.inquireAllWorks();
        Set<Works> set = new HashSet<>();
        while (set.size() < 16) {
            set.add(works.get((int) (Math.random() * works.size() - 1)));
        }
        return set;
    }
}
