package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    @Resource
    private SpeciesMapper speciesMapper;
    @Lazy
    @Resource
    private CategoryService categoryService;
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
            List<String> list = Arrays.stream(s.substring(0, s.lastIndexOf(".")).split("\\.")).toList();
            Map<Integer, List<String>> map = new HashMap<>();
            int i = 1;
            for (String s1 : list) {
                Category category = categoryService.inquireCategoryById(Integer.parseInt(s1));
                List<String> names = new ArrayList<>();
                names.add(category.getLatinName());
                names.add(category.getCName());
                map.put(i++, names);
            }
            specie.setInheritance(map);
        }
        return species;
    }

    @Override
    public List<Species> inquireAllSpecies() {
        return speciesMapper.inquireAllSpecies();
    }

    @Override
    public Set<Species> inquireSomeSpecies() {
        List<Species> species = speciesMapper.inquireSomeSpecies();
        Set<Species> set = new HashSet<>();
        while (set.size() < 6)
            set.add(species.get((int) (Math.random() * species.size() - 1)));
        return set;
    }
}
