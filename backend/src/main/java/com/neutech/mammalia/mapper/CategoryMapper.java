package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("""
            insert into t_category
            (c_name, latin_name, sub_class_count, order_count, family_count, genus_count, species_count, parent_id)
            values(#{category.cName}, #{category.latinName}, #{category.subClassCount}, #{category.orderCount}, #{category.familyCount}, #{category.genusCount}, #{category.speciesCount}, #{category.parentId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCategory(@Param("category") Category category);

    @Delete("delete from t_category where id=#{id}")
    int deleteCategoryById(@Param("id") Integer id);

    @Update("""
            update t_category set
            c_name=#{category.cName},
            latin_name=#{category.latinName},
            sub_class_count=#{category.subClassCount},
            order_count=#{category.orderCount},
            family_count=#{category.familyCount},
            genus_count=#{category.genusCount},
            species_count=#{category.speciesCount},
            parent_id=#{category.parentId}
            where id=#{category.id}
            """)
    int updateCategoryById(@Param("category") Category category);

    @Select("select * from t_category where id=#{id}")
    Category inquireCategoryById(@Param("id") Integer id);

    @Select("select * from t_category where parent_id = #{parentId}")
    List<Category> inquireCategoryByParentId(@Param("parentId") Integer parentId);

    @Select("select * from t_category where c_name = #{cName} and latin_name = #{latinName}")
    Category inquireCategoryByName(@Param("cName") String cName, @Param("latinName") String latinName);

    @Select("select * from t_category where parent_id = #{parentId} and latin_name = #{latinName}")
    Category inquireCategoryByLatinNameAndParentId(@Param("parentId") Integer parentId, @Param("latinName") String latinName);

    @Select("select * from t_category")
    List<Category> inquireAllCategories();
}
