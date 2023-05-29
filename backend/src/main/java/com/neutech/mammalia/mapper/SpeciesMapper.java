package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Species;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeciesMapper {
    @Insert("""
            insert into t_species
            (name, latin_name, genus_id, brief_introduction, detail_introduction, recommend, level, create_time, update_time)
            values (#{name}, #{latinName}, #{genusId}, #{briefIntroduction}, #{detailIntroduction}, #{recommend}, #{level}, #{createTime}, #{updateTime});
            """)
    int addSpecies(Species species);

    @Delete("delete from t_species where id = #{id};")
    int deleteSpeciesById(Integer id);

    @Update("""
            update t_species set
            name = #{name},
            latin_name = #{latinName},
            genus_id = #{genusId},
            brief_introduction = #{briefIntroduction},
            detail_introduction = #{detailIntroduction},
            recommend = #{recommend},
            level = #{level},
            create_time = #{createTime},
            update_time = #{updateTime}
            where id = #{id};
            """)
    int updateSpeciesById(Species species);

    @Select("select * from t_species where id = #{id}")
    Species inquireSpeciesById(Integer id);

    @Select("select * from t_species")
    List<Species> inquireAllSpecies();
}
