package com.neutech.mammalia.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    @Resource
    private SpeciesMapper speciesMapper;
    @Resource
    private WorksService worksService;
    @Lazy
    @Resource
    private PhotoService photoService;

    @Override

    public int addSpecies(Species species) {
        return speciesMapper.addSpecies(species);
    }

    @Override
    public int deleteSpeciesById(Integer id) {
        worksService.deleteWorksBySpeciesId(id);
        photoService.deletePhotoBySpeciesId(id);
        return speciesMapper.deleteSpeciesById(id);
    }

    @Override
    public int deleteSpeciesByGenusId(Integer genusId) {
        return speciesMapper.deleteSpeciesByGenusId(genusId);
    }

    @Override
    public int updateSpeciesById(Species species) {
        species.setUpdateTime(new Date());
        return speciesMapper.updateSpeciesById(species);
    }

    @Override
    public Species inquireSpeciesById(Integer id) {
        return speciesMapper.inquireSpeciesById(id);
    }

    @Override
    public List<Species> inquireSpeciesByKeyword(String keyword, String inheritance) {
        return speciesMapper.inquireSpeciesByKeyword(inheritance, keyword);
    }

    @Override
    public List<Species> inquireAllSpecies() {
        return speciesMapper.inquireAllSpecies();
    }
}
