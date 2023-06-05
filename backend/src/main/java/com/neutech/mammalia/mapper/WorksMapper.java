package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Works;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorksMapper {

    @Insert("""
            insert into t_works
            (user_id, species_id, image_url, create_time, update_time, is_public, view_count, like_count, comment_count)
            values(#{works.userId}, #{works.speciesId}, #{works.imageUrl}, #{works.createTime}, #{works.updateTime}, #{works.isPublic}, #{works.viewCount}, #{works.likeCount}, #{works.commentCount})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addWorks(@Param("works") Works works);

    @Delete("delete from t_works where id=#{id}")
    int deleteWorksById(@Param("id") Integer id);

    @Delete("delete from t_works where user_id = #{id}")
    int deleteWorksByUserId(@Param("id") Integer id);

    @Delete("delete from t_works where species_id = #{speciesId}")
    int deleteWorksBySpeciesId(@Param("speciesId") Integer speciesId);

    @Update("""
            update t_works set
            species_id=#{works.speciesId},
            image_url=#{works.imageUrl},
            is_public=#{works.isPublic},
            view_count=#{works.viewCount},
            like_count=#{works.likeCount},
            comment_count=#{works.commentCount}
            where id=#{works.id}
            """)
    int updateWorksById(@Param("works") Works works);

    @Select("select * from t_works where id=#{id}")
    Works inquireWorksById(@Param("id") Integer id);

    @Select("select * from t_works where user_id = #{userId}")
    List<Works> inquireWorksIdByUserId(@Param("userId") Integer userId);

    @Select("select * from t_works")
    List<Works> inquireAllWorks();
}
