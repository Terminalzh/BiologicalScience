package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("""
            insert into t_banner
            (species_image_id)
            values(#{banner.speciesImage.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addBanner(@Param("banner") Banner banner);

    @Delete("delete from t_banner where id=#{id}")
    int deleteBannerById(@Param("id") Integer id);

    @Delete("delete from t_banner where species_image_id = #{speciesImageId}")
    int deleteBannerBySpeciesImageId(@Param("speciesImageId") Integer speciesImageId);

    @Delete("delete from t_banner")
    void deleteAllBanner();

    @Update("""
            update t_banner set
            species_image_id=#{banner.speciesImage.id},
            update_time=#{banner.updateTime}
            where id=#{banner.id}
            """)
    int updateBannerById(@Param("banner") Banner banner);

    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "species_image_id", property = "speciesImage",
                    one = @One(select = "com.neutech.mammalia.mapper.SpeciesImageMapper.inquireSpeciesImageById"))
    })
    @Select("select * from t_banner where id=#{id}")
    Banner inquireBannerById(@Param("id") Integer id);

    @Select("select * from t_banner")
    List<Banner> inquireAllBanner();
}
