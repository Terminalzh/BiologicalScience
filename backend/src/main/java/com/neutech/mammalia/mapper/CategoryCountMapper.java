package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.CategoryCount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryCountMapper {
    @Insert("insert into category_count (id ,categorized_inheritance) values (#{categoryCount.id},#{categoryCount.categorizedInheritance})")
    int addCategoryCount(@Param("categoryCount") CategoryCount categoryCount);

    @Delete("delete from category_count where id = #{id}")
    int deleteCategoryCountById(@Param("id") Integer id);

    @Update("""
            update category_count set
            sub_class = #{categoryCount.subClass},
            order_count = #{categoryCount.orderCount},
            family = #{categoryCount.family},
            genus = #{categoryCount.genus},
            species = #{categoryCount.species}
            where id = #{categoryCount.id}
            """)
    int updateCategoryCountById(@Param("categoryCount") CategoryCount categoryCount);

    @Select("select COUNT(0) from category_count where categorized_inheritance regexp CONCAT('^', #{categorizedInheritance}, '$')")
    int inquireSubCategoryCount(@Param("categorizedInheritance") String categorizedInheritance);

    @Select("select * from category_count where categorized_inheritance regexp #{expression}")
    List<CategoryCount> inquireAllCategories(@Param("expression") String expression);

    @Select("select categorized_inheritance from category_count where id = #{id}")
    String inquireCategorizedInheritance(@Param("id") Integer id);
}
