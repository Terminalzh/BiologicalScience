package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.sqlProvider.SpeciesSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeciesMapper {
    @Insert("""
            insert into t_species
            (c_name, latin_name, genus_id, brief_introduction, detail_introduction, recommend, level,picture_url,better_url,
            create_time,update_time)
            values (#{species.cName}, #{species.latinName}, #{species.genusId}, #{species.briefIntroduction},
            #{species.detailIntroduction}, #{species.recommend}, #{species.level}, #{species.pictureUrl},#{species.betterUrl},
            current_timestamp,current_timestamp);
            """)
    int addSpecies(@Param("species") Species species);

    @Delete("delete from t_species where id = #{id};")
    int deleteSpeciesById(@Param("id") Integer id);

    @Delete("delete from t_species where genus_id = #{genusId}")
    int deleteSpeciesByGenusId(@Param("genusId") Integer genusId);

    @UpdateProvider(value = SpeciesSqlProvider.class, method = "updateSpeciesById")
    int updateSpeciesById(@Param("species") Species species);

    @Select("select * from t_species where id = #{id}")
    Species inquireSpeciesById(@Param("id") Integer id);

    @Select("select * from t_species where genus_id = #{genusId}")
    List<Species> inquireSpeciesByGenusId(@Param("genusId") Integer genusId);

    @Select("""
            select * from t_species where
            (c_name like concat('%',#{keyword},'%') or
            latin_name like concat('%',#{keyword},'%'))
            and t_species.id in
            (select category_count.id from category_count
            where categorized_inheritance regexp concat('^',#{inheritance})
            )
            """)
    List<Species> inquireSpeciesByKeyword(@Param("inheritance") String inheritance, @Param("keyword") String keyword);

    @Select("select * from t_species")
    List<Species> inquireAllSpecies();
}
