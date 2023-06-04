package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.mapper.sqlProvider.SpeciesSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeciesMapper {
    @Insert("""
            insert into t_species
            (c_name, latin_name, genus_id, brief_introduction, detail_introduction, recommend, level)
            values (#{species.cName}, #{species.latinName}, #{species.genusId}, #{species.briefIntroduction}, #{species.detailIntroduction}, #{species.recommend}, #{species.level});
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

    @Select("select * from t_species")
    List<Species> inquireAllSpecies();
}
