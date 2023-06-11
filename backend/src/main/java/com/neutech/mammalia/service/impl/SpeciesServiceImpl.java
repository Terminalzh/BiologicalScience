package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.bean.Species.CategoryParam;
import com.neutech.mammalia.mapper.SpeciesMapper;
import com.neutech.mammalia.service.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
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
        if (categoryService.inquireCategoryByName(species.getLatinName(), species.getCName()) != null)
            return 0;
        List<Category> categories = new ArrayList<>();
        StringBuilder inheritance = new StringBuilder("1");
        Map<String, CategoryParam> categoryParam = species.getCategory();
        CategoryParam speciesParam = new CategoryParam();
        speciesParam.setCName(species.getCName());
        speciesParam.setLatinName(species.getLatinName());
        speciesParam.setId(species.getId());
        categoryParam.put("species", speciesParam);
        categories.add(add(categoryParam.get("sub_class"), 1, inheritance));
        categories.add(add(categoryParam.get("order"), categories.get(0).getId(), inheritance));
        categories.add(add(categoryParam.get("family"), categories.get(1).getId(), inheritance));
        categories.add(add(categoryParam.get("genus"), categories.get(2).getId(), inheritance));
        categories.add(add(categoryParam.get("species"), categories.get(3).getId(), inheritance));
        species.setGenusId(categories.get(3).getId());
        return speciesMapper.addSpecies(species);
    }

    public Category add(CategoryParam categoryParam, Integer parentId, StringBuilder inheritance) {
        Category category;
        if (categoryService.inquireCategoryByName(categoryParam.getLatinName(), categoryParam.getLatinName()) == null && categoryParam.getId() < 0) {
            category = new Category();
            category.setParentId(parentId);
            category.setLatinName(categoryParam.getLatinName());
            category.setCName(categoryParam.getCName());
            categoryService.addCategory(category);
            CategoryCount categoryCount = new CategoryCount();
            category = categoryService.inquireCategoryByName(categoryParam.getLatinName(), categoryParam.getCName());
            categoryCount.setId(category.getId());
            inheritance.append(".").append(categoryCount.getId());
            categoryCount.setCategorizedInheritance(inheritance.toString());
            categoryCountService.addCategoryCount(categoryCount);
        } else {
            category = categoryService.inquireCategoryById(categoryParam.getId());
            inheritance.append(".").append(category.getId());
        }
        return category;
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
            List<String> list = Arrays.stream(s.split("\\.")).toList();
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
        List<Species> species = speciesMapper.inquireAllSpecies();
        for (Species specie : species) {
            String s = categoryCountService.inquireCategorizedInheritanceById(specie.getGenusId());
            List<String> list = Arrays.stream(s.split("\\.")).toList();
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
    public Set<Species> inquireSomeSpecies() {
        List<Species> species = speciesMapper.inquireSomeSpecies();
        Set<Species> set = new HashSet<>();
        while (set.size() < 6) {
            Species specie = species.get((int) (Math.random() * species.size() - 1));
            if (!set.contains(specie)) {
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
                set.add(specie);
            }
        }
        return set;
    }
}
