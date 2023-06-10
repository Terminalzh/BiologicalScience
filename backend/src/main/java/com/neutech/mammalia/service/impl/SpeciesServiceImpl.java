package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.CategoryCountService;
import com.neutech.mammalia.service.PhotoService;
import com.neutech.mammalia.service.SpeciesService;
import com.neutech.mammalia.service.WorksService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    @Resource
    private SpeciesMapper speciesMapper;
    @Resource
    private WorksService worksService;
    @Lazy
    @Resource
    private PhotoService photoService;
    @Lazy
    @Resource
    private CategoryCountService categoryCountService;

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
        List<Species> species = speciesMapper.inquireSpeciesByKeyword(inheritance, keyword);
        for (Species specie : species) {
            String s = categoryCountService.inquireCategorizedInheritanceById(specie.getGenusId());
            specie.setCategorizedInheritance(s.substring(0, s.lastIndexOf(".")));
        }
        return species;
    }

    @Override
    public List<Species> inquireAllSpecies() {
        return speciesMapper.inquireAllSpecies();
    }
}
