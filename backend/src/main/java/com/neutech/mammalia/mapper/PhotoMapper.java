package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotoMapper {

    @Insert("""
            insert into t_photo
            (species_id, works_id, is_public, view_count, like_count, comment_count, create_time, update_time)
            values(#{photo.speciesId}, #{photo.worksId}, #{photo.isPublic}, #{photo.viewCount}, #{photo.likeCount}, #{photo.commentCount}, #{photo.createTime}, #{photo.updateTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addPhoto(@Param("photo") Photo photo);

    @Delete("delete from t_photo where id=#{id}")
    int deletePhotoById(@Param("id") Integer id);

    @Update("""
            update t_photo set
            species_id=#{photo.speciesId},
            works_id=#{photo.worksId},
            is_public=#{photo.isPublic},
            view_count=#{photo.viewCount},
            like_count=#{photo.likeCount},
            comment_count=#{photo.commentCount},
            create_time=#{photo.createTime},
            update_time=#{photo.updateTime}
            where id=#{photo.id}
            """)
    int updatePhotoById(@Param("photo") Photo photo);

    @Select("select * from t_photo where id=#{id}")
    Photo inquirePhotoById(@Param("id") Integer id);

    @Select("select * from t_photo")
    List<Photo> inquireAllPhotos();
}
