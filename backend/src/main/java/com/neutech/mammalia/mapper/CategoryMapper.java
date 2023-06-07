package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.mapper.sqlProvider.CategorySqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("""
            insert into t_category
            (c_name, latin_name, parent_id)
            values(#{category.cName}, #{category.latinName}, #{category.parentId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCategory(@Param("category") Category category);

    @Delete("delete from t_category where id=#{id}")
    int deleteCategoryById(@Param("id") Integer id);

    @UpdateProvider(value = CategorySqlProvider.class, method = "updateCategoryById")
    int updateCategoryById(@Param("category") Category category);

    @Select("select * from t_category where id=#{id}")
    Category inquireCategoryById(@Param("id") Integer id);

    @Select("select * from t_category where parent_id = #{parentId}")
    List<Category> inquireCategoryByParentId(@Param("parentId") Integer parentId);

    @Select("select * from t_category where c_name = #{cName} and latin_name = #{latinName}")
    Category inquireCategoryByName(@Param("cName") String cName, @Param("latinName") String latinName);

    @Select("select * from t_category where parent_id = #{parentId} and latin_name = #{latinName}")
    Category inquireCategoryByLatinNameAndParentId(@Param("parentId") Integer parentId, @Param("latinName") String latinName);
    @Select("select count(0) from t_category where parent_id = #{parentId}")
    int inquireCategoryCountByParentId(@Param("parentId") Integer parentId);
}
