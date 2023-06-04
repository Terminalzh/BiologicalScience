package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.SpeciesImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeciesImageMapper {

    @Insert("""
            insert into species_image
            (species_id, path)
            values( #{speciesImage.speciesId}, #{speciesImage.path})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addSpeciesImage(@Param("speciesImage") SpeciesImage speciesImage);

    @Delete("delete from species_image where id=#{id}")
    int deleteSpeciesImageById(@Param("id") Integer id);

    @Delete("delete from species_image where species_id = #{speciesId}")
    int deleteSpeciesImageBySpeciesId(@Param("speciesId") Integer speciesId);

    @Update("""
            update species_image set
            species_id=#{speciesImage.speciesId},
            path=#{speciesImage.path},
            update_time=#{speciesImage.updateTime}
            where id=#{speciesImage.id}
            """)
    int updateSpeciesImageById(@Param("speciesImage") SpeciesImage speciesImage);

    @Select("select * from species_image where id=#{id}")
    SpeciesImage inquireSpeciesImageById(@Param("id") Integer id);

    @Select("select * from species_image")
    List<SpeciesImage> inquireAllSpeciesImages();
}
