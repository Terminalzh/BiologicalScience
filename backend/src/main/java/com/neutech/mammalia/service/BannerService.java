package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Banner;

import java.util.List;

public interface BannerService {
    int addBanner(Integer speciesId);

    int deleteBannerById(Integer id);

    int deleteBannerBySpeciesId(int speciesId);

    int updateBannerById(Integer id, Integer speciesId);

    Banner inquireBannerById(Integer id);

    Banner inquireBannerBySpeciesId(Integer speciesId);

    List<Banner> inquireAllBanner();

}
