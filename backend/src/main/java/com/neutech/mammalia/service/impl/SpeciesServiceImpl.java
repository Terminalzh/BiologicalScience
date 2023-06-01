package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    @Resource
    private SpeciesMapper speciesMapper;
    @Override
    public int addSpecies(Species species) {
        return 0;
    }

    @Override
    public int deleteSpeciesById(Integer id) {
        return 0;
    }

    @Override
    public int updateSpeciesById(Species species) {
        return 0;
    }

    @Override
    public Species inquireSpeciesById(Integer id) {
        return null;
    }

    @Override
    public List<Species> inquireAllSpecies() {
        return null;
    }
}
