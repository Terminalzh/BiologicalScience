package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("""
            insert into t_category
            (clazz, orders, family, genus, species)
            VALUES(#{category.clazz}, #{category.orders}, #{category.family}, #{category.genus}, #{category.species})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCategory(@Param("category") Category category);

    @Delete("delete from t_category where id = #{id}")
    int deleteCategoryById(@Param("id") Integer id);

    @Update("""
            update t_category set
            clazz=#{category.clazz},
            orders=#{category.orders},
            family=#{category.family},
            genus=#{category.genus},
            species=#{category.species}
            where id=#{category.id}
            """)
    int updateCategoryById(@Param("category") Category category);

    @Select("select id, clazz, orders, family, genus, species from t_category where id = #{id}")
    Category inquireCategoryById(@Param("id") Integer id);

    @Select("select id, clazz, orders, family, genus, species from t_category")
    List<Category> inquireAllCategory();
}
