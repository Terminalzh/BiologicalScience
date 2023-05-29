package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Works;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorksMapper {
    @Insert("""
            insert into t_works
            (user_id, title, description, image_url, create_time, update_time, is_public, view_count, like_count, comment_count, photo_wall_id)
            values (#{userId}, #{title}, #{description}, #{imageUrl}, #{createTime}, #{updateTime}, #{isPublic}, #{viewCount}, #{likeCount}, #{commentCount}, #{photoWallId});
            """)
    int addWorks(Works works);

    @Delete("delete from t_works where id = #{id};")
    int deleteWorksById(Integer id);

    @Update("""
            update t_works set
            user_id = #{userId},
            title = #{title},
            description = #{description},
            image_url = #{imageUrl},
            create_time = #{createTime},
            update_time = #{updateTime},
            is_public = #{isPublic},
            view_count = #{viewCount},
            like_count = #{likeCount},
            comment_count = #{commentCount},
            photo_wall_id = #{photoWallId}
            where id = #{id};
            """)
    int updateWorksById(Works works);

    @Select("select * from t_works where id = #{id}")
    Works inquireWorksById(Integer id);

    @Select("select * from t_works where user_id = #{userId}")
    List<Works> inquireAllWorksByUserId(Integer userId);

    @Select("""
            select t_works.*
            from t_works
            inner join t_photo on t_photo.id = t_works.photo_wall_id
            where t_photo.id = #{photoWallId}
            """)
    List<Works> inquireAllWorksByPhotoWallId(Integer photoWallId);

    @Select("select * from t_works")
    List<Works> inquireAllWorks();
}
