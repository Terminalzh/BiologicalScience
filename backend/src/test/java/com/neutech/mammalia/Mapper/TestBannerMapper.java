package com.neutech.mammalia.Mapper;

import com.neutech.mammalia.bean.Banner;
import com.neutech.mammalia.mapper.BannerMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest
public class TestBannerMapper {
    @Autowired
    BannerMapper mapper;

    @Test
    void testAddBanner() {
        Map<Integer, Banner> map = new HashMap<>();
        while (map.size() < 1000) {
            Banner banner = getBanner();
            banner.setImageUrl("/static/image/Charlotte_Katakuri.jpg");
            map.put(banner.getId(), banner);
        }
        map.forEach((key, val) -> mapper.addBanner(val));
    }

    @Test
    void testDeleteBannerById() {
        int count = 0;
        for (int i = 0; i < 1001; i++) {
            count += mapper.deleteBannerById(i);
        }
        System.out.println(count);
    }

    @Test
    void testUpdateBannerById() {
        List<Banner> banners = mapper.inquireAllBanner();
        int[] count = {0};
        banners.forEach(item -> {
            item.setImageUrl("/static/image/Charlotte_Katakuri.jpg");
            count[0] += mapper.updateBannerById(item);
        });
        System.out.println(count[0]);
    }

    @Test
    void testInquireBannerBuId() {
        for (int i = 10; i < 20; i++)
            System.out.println(mapper.inquireBannerBuId(i));
    }

    @Test
    void testInquireAllBanner() {
        List<Banner> banners = mapper.inquireAllBanner();
        banners.forEach(System.out::println);
    }

    Banner getBanner() {
        // Create a new Banner object
        Banner banner = new Banner();
        // Use a random number generator to assign random values to the object's properties
        Random rand = new Random();
        banner.setId(rand.nextInt(1000000)); // Random integer between 0 and 999999
        // Random image URL
        banner.setImageUrl("https://example.com/banner" + rand.nextInt(10) + ".jpg");
        // Random date within the last 24 hours
        banner.setCreatedTime(
                new Date(System.currentTimeMillis() - rand.nextInt(86400000)));
        // Random date within the last 24 hours
        banner.setUpdatedTime(
                new Date(System.currentTimeMillis() - rand.nextInt(86400000)));
        return banner;
    }

    @Test
    void getFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("/static/image/Charlotte_Katakuri.jpg");
        String content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(content);
    }
}
