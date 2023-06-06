package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("""
            insert into t_banner
            (species_id,create_time,update_time)
            values(#{banner.species.id},#{banner.createTime},#{banner.updateTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addBanner(@Param("banner") Banner banner);

    @Delete("delete from t_banner where id=#{id}")
    int deleteBannerById(@Param("id") Integer id);

    @Delete("delete from t_banner where species_id = #{speciesId}")
    int deleteBannerBySpeciesImageId(@Param("speciesId") Integer speciesId);

    @Update("""
            update t_banner set
            species_id=#{newSpeciesId},
            update_time = current_timestamp
            where species_id=#{oldSpeciesId}
            """)
    int updateBannerById(@Param("oldSpeciesId") Integer oldSpeciesId, @Param("newSpeciesId") Integer newSpeciesId);

    @Results(id = "BannerResultMapping", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "species_id", property = "species",
                    one = @One(select = "com.neutech.mammalia.mapper.SpeciesMapper.inquireSpeciesById")),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from t_banner where id=#{id}")
    Banner inquireBannerById(@Param("id") Integer id);

    @ResultMap(value = "BannerResultMapping")
    @Select("select * from t_banner where species_id = #{speciesId}")
    Banner inquireBannerBySpeciesId(@Param("speciesId") Integer speciesId);

    @ResultMap(value = "BannerResultMapping")
    @Select("select * from t_banner")
    List<Banner> inquireAllBanner();
}
