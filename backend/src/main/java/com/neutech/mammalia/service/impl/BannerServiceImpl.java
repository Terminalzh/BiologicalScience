package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.mapper.BannerMapper;
import com.neutech.mammalia.service.BannerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerMapper bannerMapper;

    @Override
    public int addBanner(Banner banner) {
        return 0;
    }

    @Override
    public int deleteBannerById(Integer id) {
        return 0;
    }

    @Override
    public int updateBannerById(Banner banner) {
        return 0;
    }

    @Override
    public Banner inquireBannerById(Integer id) {
        return null;
    }

    @Override
    public List<Banner> inquireAllBanner() {
        return null;
    }
}
