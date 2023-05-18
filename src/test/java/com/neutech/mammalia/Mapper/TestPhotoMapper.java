package com.neutech.mammalia.Mapper;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.mapper.PhotoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPhotoMapper {
    @Autowired
    private PhotoMapper photoMapper;

    @Test
    void testInquire() {
        Photo photo = photoMapper.inquirePhotoById(555);
        System.out.println(photo);
    }

    @Test
    void testUpdate() {
        Photo photo = photoMapper.inquirePhotoById(555);
        System.out.println(photoMapper.updatePhotoById(photo));
    }


}
