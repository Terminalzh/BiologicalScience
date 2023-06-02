package com.neutech.mammalia.Mapper;

import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.mapper.BannerMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BannerMapperTest {
    @Resource
    BannerMapper mapper;

    @Test
    void addBanner() {
    }

    @Test
    void deleteBannerById() {
    }

    @Test
    void updateBannerById() {
    }

    @Test
    void inquireBannerBuId() {
    }

    @Test
    void inquireAllBanner() {
        List<Banner> banners = mapper.inquireAllBanner();
        System.out.println(banners);
    }
}
