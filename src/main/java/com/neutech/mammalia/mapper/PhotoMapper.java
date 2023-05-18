package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotoMapper {
    @Insert("""
            insert into t_photo
            (user_id, type, title, description, cover_image_url, create_time, update_time, is_public, view_count, like_count, comment_count)
            values (#{photo.userId}, #{photo.type}, #{photo.title}, #{photo.description}, #{photo.coverImageUrl}, #{photo.createTime}, #{photo.updateTime}, #{photo.isPublic}, #{photo.viewCount}, #{photo.likeCount}, #{photo.commentCount});
            """)
    int addPhoto(@Param("photo") Photo photo);

    @Delete("delete from t_photo where id = #{id};")
    int deletePhotoById(@Param("id") Integer id);

    @Update("""
            update t_photo set
            user_id = #{photo.userId},
            type = #{photo.type},
            title = #{photo.title},
            description = #{photo.description},
            cover_image_url = #{photo.coverImageUrl},
            create_time = #{photo.createTime},
            update_time = #{photo.updateTime},
            is_public = #{photo.isPublic},
            view_count = #{photo.viewCount},
            like_count = #{photo.likeCount},
            comment_count = #{photo.commentCount}
            where id = #{photo.id};
            """)
    int updatePhotoById(@Param("photo") Photo photo);

    @Select("select * from t_photo where id = #{id}")
    Photo inquirePhotoById(@Param("id") Integer id);

    @Select("select * from t_photo")
    List<Photo> inquireAllPhoto();
}
