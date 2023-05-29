package com.neutech.mammalia;

import com.neutech.mammalia.bean.*;
import com.neutech.mammalia.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class BiologicalScienceApplicationTests {
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    PhotoMapper photoMapper;
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    SpeciesMapper speciesMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WorksMapper worksMapper;

    @Test
    void addUser() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            User user = new User();
            user.setName("user_" + uuid.substring(0, 8));
            user.setPassword(uuid.substring(9, 13));
            user.setGender(random.nextBoolean() ? "男" : "女");
            user.setPhone("1" +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10) +
                          random.nextInt(10)
            );
            user.setCreateTime(new Date());
            userMapper.addUser(user);
        }
    }

    @Test
    void addReport() {
        List<User> users = userMapper.inquireAllUser();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String reason = UUID.randomUUID().toString();
            User user = users.get(random.nextInt(1000));
            Report report = new Report();
            report.setReporterUserId(user.getId());
            report.setReportedUserId(user.getId());
            report.setReportReason(reason);
            report.setReportTime(new Date());
            reportMapper.addReport(report);
        }
    }

    @Test
    void addCategory() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Category category = new Category();
            category.setClazz("Clazz " + (random.nextInt(1000) + 1));
            category.setOrders("Orders " + (random.nextInt(1000) + 1));
            category.setFamily("Family " + (random.nextInt(1000) + 1));
            category.setGenus("Genus " + (random.nextInt(1000) + 1));
            category.setSpecies("Species " + (random.nextInt(1000) + 1));
            categoryMapper.addCategory(category);
        }
    }

    @Test
    void addSpecies() {
        Random random = new Random();
        List<Category> categories = categoryMapper.inquireAllCategory();
        for (int i = 0; i < 1000; i++) {
            Species species = new Species();
            int nextInt = random.nextInt(1000000);
            species.setName("Species " + nextInt);
            species.setLatinName("Latin Name " + nextInt);
            species.setGenusId(categories.get(random.nextInt(1000)).getId());
            species.setBriefIntroduction("briefIntroduction " + random.nextInt(1000));
            species.setDetailIntroduction("detailIntroduction " + random.nextInt(1000));
            species.setRecommend(random.nextInt(2) == 0);
            species.setLevel(random.nextInt(4));
            species.setCreateTime(new Date());
            species.setUpdateTime(new Date());
            speciesMapper.addSpecies(species);
        }
    }

    @Test
    void addWorks() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Works works = new Works();
            works.setUserId(random.nextInt(1000) + 1);
            works.setTitle(UUID.randomUUID().toString().substring(9, 13));
            works.setDescription(UUID.randomUUID().toString());
            works.setImageUrl("/static/image/Charlotte_Katakuri.jpg");
            works.setCreateTime(new Date());
            works.setUpdateTime(new Date());
            works.setIsPublic(Math.random() < 0.5);
            works.setViewCount((int) (Math.random() * 1000));
            works.setLikeCount((int) (Math.random() * 1000));
            works.setCommentCount((int) (Math.random() * 1000));
            worksMapper.addWorks(works);
        }
    }

    @Test
    void testUpdateWorks() {
        List<Works> works = worksMapper.inquireAllWorks();
        Random random = new Random();
        for (Works work : works) {
            work.setPhotoWallId(random.nextInt(1000) + 1);
            worksMapper.updateWorksById(work);
        }
    }

    @Test
    void addPhoto() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Photo photo = new Photo();
            photo.setUserId(random.nextInt(1000) + 1);
            photo.setTitle(UUID.randomUUID().toString().substring(9, 13));
            photo.setType(UUID.randomUUID().toString().substring(14, 18));
            photo.setDescription(UUID.randomUUID().toString());
            photo.setCoverImageUrl("/static/image/Charlotte_Katakuri.jpg");
            photo.setCreateTime(new Date());
            photo.setUpdateTime(new Date());
            photo.setIsPublic(Math.random() < 0.5);
            photo.setViewCount((int) (Math.random() * 1000));
            photo.setLikeCount((int) (Math.random() * 1000));
            photo.setCommentCount((int) (Math.random() * 1000));
            photoMapper.addPhoto(photo);
        }
    }

    @Test
    void addBanner() {
        for (int i = 0; i < 1000; i++) {
            Banner banner = new Banner();
            banner.setImageUrl("/static/image/Charlotte_Katakuri.jpg");
            banner.setCreatedTime(new Date());
            banner.setUpdatedTime(new Date());
            bannerMapper.addBanner(banner);
        }
    }
}
