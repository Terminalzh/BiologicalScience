package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.SpeciesImage;

import java.util.List;

public interface SpeciesImageService {
    int addSpeciesImage(SpeciesImage speciesImage);

    int deleteSpeciesImageById(Integer id);

    int deleteSpeciesImageBySpeciesId(Integer speciesId);

    int updateSpeciesImageById(SpeciesImage speciesImage);

    SpeciesImage inquireSpeciesImageById(Integer id);

    List<SpeciesImage> inquireAllSpeciesImages();

}
