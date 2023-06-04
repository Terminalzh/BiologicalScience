package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Banner;

import java.util.List;

public interface BannerService {
    int addBanner(Banner banner);

    int deleteBannerById(Integer id);

    int deleteBannerByWorksId(int WorksId);

    int deleteBannerBySpeciesId(Integer speciesId);

    int updateBannerById(Banner banner);

    Banner inquireBannerById(Integer id);

    List<Banner> inquireAllBanner();

}
