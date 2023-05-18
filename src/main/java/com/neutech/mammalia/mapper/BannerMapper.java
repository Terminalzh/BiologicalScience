package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("""
            insert into t_banner
            (image_url, created_time, updated_time)
            values(#{banner.imageUrl}, #{banner.createdTime}, #{banner.updatedTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addBanner(@Param("banner") Banner banner);

    @Delete("delete from t_banner where id=#{id}")
    int deleteBannerById(@Param("id") Integer id);

    @Update("""
            update t_banner set
            image_url=#{banner.imageUrl},
            created_time=#{banner.createdTime},
            updated_time=#{banner.updatedTime}
            where id=#{banner.id}
            """)
    int updateBannerById(@Param("banner") Banner banner);

    @Select("select id, image_url, created_time, updated_time from t_banner where id=#{id}")
    Banner inquireBannerBuId(@Param("id") Integer id);

    @Select("select id, image_url, created_time, updated_time from t_banner")
    List<Banner> inquireAllBanner();

}
