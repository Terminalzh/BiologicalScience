package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Species;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeciesMapper {
    @Insert("""
            insert into t_species
            (name, latin_name, genus_id, brief_introduction, detail_introduction, recommend, level, create_time, update_time)
            values (#{species.name}, #{species.latinName}, #{species.genusId}, #{species.briefIntroduction}, #{species.detailIntroduction}, #{species.recommend}, #{species.level}, #{species.createTime}, #{species.updateTime});
            """)
    int addSpecies(@Param("species") Species species);

    @Delete("delete from t_species where id = #{id};")
    int deleteSpeciesById(@Param("id") Integer id);

    @Update("""
            update t_species set
            name = #{species.name},
            latin_name = #{species.latinName},
            genus_id = #{species.genusId},
            brief_introduction = #{species.briefIntroduction},
            detail_introduction = #{species.detailIntroduction},
            recommend = #{species.recommend},
            level = #{species.level},
            create_time = #{species.createTime},
            update_time = #{species.updateTime}
            where id = #{id};
            """)
    int updateSpeciesById(@Param("species") Species species);

    @Select("select * from t_species where id = #{id}")
    Species inquireSpeciesById(@Param("id") Integer id);

    @Select("select * from t_species")
    List<Species> inquireAllSpecies();
}
