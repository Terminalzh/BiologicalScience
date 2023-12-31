package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Species;

import java.util.List;
import java.util.Set;

public interface SpeciesService {
    int addSpecies(Species species);

    int deleteSpeciesById(Integer id);

    int deleteSpeciesByGenusId(Integer genusId);

    int updateSpeciesById(Species species);

    Species inquireSpeciesById(Integer id);

    List<Species> inquireSpeciesByKeyword(String keyword, String inheritance);

    List<Species> inquireAllSpecies();

    Set<Species> inquireSomeSpecies();
}
