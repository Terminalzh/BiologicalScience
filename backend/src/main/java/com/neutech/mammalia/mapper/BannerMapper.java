package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("""
            insert into t_banner
            (species_id, works_id, created_time, updated_time)
            values(#{banner.speciesId}, #{banner.worksId}, #{banner.createdTime}, #{banner.updatedTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addBanner(@Param("banner") Banner banner);

    @Delete("delete from t_banner where id=#{id}")
    int deleteBannerById(@Param("id") Integer id);

    @Update("""
            update t_banner set
            species_id=#{banner.speciesId},
            works_id=#{banner.worksId},
            created_time=#{banner.createdTime},
            updated_time=#{banner.updatedTime}
            where id=#{banner.id}
            """)
    int updateBannerById(@Param("banner") Banner banner);

    @Select("select * from t_banner where id=#{id}")
    Banner inquireBannerBuId(@Param("id") Integer id);

    @Select("select * from t_banner")
    List<Banner> inquireAllBanner();

}
