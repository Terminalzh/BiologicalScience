package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Works;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorksMapper {

    @Insert("""
            insert into t_works
            (user_id, species_id, image_url, create_time, update_time, is_public, view_count, like_count, comment_count)
            values(#{works.user.id}, #{works.species.id}, #{works.imageUrl}, #{works.createTime},
            #{works.updateTime}, #{works.isPublic}, #{works.viewCount}, #{works.likeCount}, #{works.commentCount})
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
            species_id=#{works.species.id},
            image_url=#{works.imageUrl},
            is_public=#{works.isPublic},
            view_count=#{works.viewCount},
            like_count=#{works.likeCount},
            comment_count=#{works.commentCount}
            where id=#{works.id}
            """)
    int updateWorksById(@Param("works") Works works);

    @Results(id = "worksResultMapping", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user", one = @One(select = "com.neutech.mammalia.mapper.UserMapper.inquireUserById")),
            @Result(column = "species_id", property = "species", one = @One(select = "com.neutech.mammalia.mapper.SpeciesMapper.inquireSpeciesById")),
            @Result(column = "image_url", property = "imageUrl"),
            @Result(column = "is_public", property = "isPublic"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "view_count", property = "viewCount"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "comment_count", property = "commentCount"),
    })
    @Select("select * from t_works where id=#{id}")
    Works inquireWorksById(@Param("id") Integer id);

    @Select("select * from t_works where user_id = #{userId}")
    List<Works> inquireWorksIdByUserId(@Param("userId") Integer userId);

    @Select("select * from t_works")
    List<Works> inquireAllWorks();
}
