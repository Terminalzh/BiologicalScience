package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("""
            insert into t_banner
            (species_id, works_id)
            values(#{banner.speciesId}, #{banner.worksId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addBanner(@Param("banner") Banner banner);

    @Delete("delete from t_banner where id=#{id}")
    int deleteBannerById(@Param("id") Integer id);

    @Delete("delete from t_banner where works_id = #{worksId}")
    int deleteBannerByWorksId(@Param("worksId") Integer worksId);

    @Update("""
            update t_banner set
            species_id=#{banner.speciesId},
            works_id=#{banner.worksId},
            update_time=#{banner.updateTime}
            where id=#{banner.id}
            """)
    int updateBannerById(@Param("banner") Banner banner);

    @Select("select * from t_banner where id=#{id}")
    Banner inquireBannerById(@Param("id") Integer id);

    @Select("select * from t_banner")
    List<Banner> inquireAllBanner();
}
