package com.neutech.mammalia.mapper.sqlProvider;

import com.neutech.mammalia.bean.Category;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

public class CategorySqlProvider {
    public static String updateCategoryById(Category category) {
        SQL sql = new SQL();
        sql.UPDATE("t_category");
        if (!Objects.equals(category.getCName(), "") && category.getCName() != null) {
            sql.SET("c_name = #{category.cName}");
        }
        if (!Objects.equals(category.getLatinName(), "") && category.getLatinName() != null) {
            sql.SET("latin_name = #{category.latinName}");
        }
        sql.WHERE("id = #{category.id}");
        return sql.toString();
    }
}
