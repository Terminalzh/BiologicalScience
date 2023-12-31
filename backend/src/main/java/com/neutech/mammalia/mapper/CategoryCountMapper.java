package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.bean.CategoryFlat;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryCountMapper {
    @Insert("insert into category_count (id ,categorized_inheritance) values (#{categoryCount.id},#{categoryCount.categorizedInheritance})")
    int addCategoryCount(@Param("categoryCount") CategoryCount categoryCount);

    @Insert("""
            insert into category_count (id, categorized_inheritance, sub_class, order_count, family, genus, species)
            values (#{id},#{categorizedInheritance},#{subClass},#{orderCount},#{family},#{genus},#{species});
            """)
    int add(CategoryCount categoryCount);

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

    @Select("select * from category_count where categorized_inheritance regexp #{expression} order by id desc limit #{start},#{pageSize} ")
    List<CategoryCount> inquireAllCategories(@Param("expression") String expression, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select("""
            select * from category_count
            left join t_species ts on category_count.id = ts.id
            where categorized_inheritance regexp concat('^',#{inheritance})
            """)
    List<CategoryCount> inquireCategoryFlatByInheritance(@Param("inheritance") String inheritance);

    @Select("select categorized_inheritance from category_count where id = #{id}")
    String inquireCategorizedInheritance(@Param("id") Integer id);

    @Select("select * from category_count where id = #{id}")
    CategoryCount inquireCategoryCountById(@Param("id") Integer id);

    @Select("select count(id) from category_count where categorized_inheritance regexp #{expression}")
    Integer inquirePageCount(@Param("expression") String expression);
}
