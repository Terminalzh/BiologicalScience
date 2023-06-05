package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Banner;

import java.util.List;

public interface BannerService {
    int addBanner(Integer speciesImageId);

    int deleteBannerById(Integer id);

    int deleteBannerBySpeciesImageId(int speciesImageId);

    int updateBannerById(Integer id, Integer speciesImageId);

    Banner inquireBannerById(Integer id);

    List<Banner> inquireAllBanner();

}
