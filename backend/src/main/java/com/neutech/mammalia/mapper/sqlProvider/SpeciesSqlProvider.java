package com.neutech.mammalia.mapper.sqlProvider;

import com.neutech.mammalia.bean.Species;
import org.apache.ibatis.jdbc.SQL;

public class SpeciesSqlProvider {
    public static String updateSpeciesById(Species species) {
        SQL sql = new SQL();
        sql.UPDATE("t_species");
        if (species.getCName() != null)
            sql.SET("c_name = #{species.cName}");
        if (species.getLatinName() != null)
            sql.SET("latin_name = #{species.latinName}");
        if (species.getGenusId() != null)
            sql.SET("genus_id = #{species.genusId}");
        if (species.getBriefIntroduction() != null)
            sql.SET("brief_introduction = #{species.briefIntroduction}");
        if (species.getDetailIntroduction() != null)
            sql.SET("detail_introduction = #{species.detailIntroduction}");
        if (species.getRecommend() != null)
            sql.SET("recommend = #{species.recommend}");
        if (species.getLevel() != null)
            sql.SET("level = #{species.level}");
        if (species.getPictureUrl() != null)
            sql.SET("pictureUrl = #{species.pictureUrl}");
        sql.SET("update_time = CURRENT_TIMESTAMP");
        sql.WHERE("id = #{species.id}");
        return sql.toString();
    }
}
