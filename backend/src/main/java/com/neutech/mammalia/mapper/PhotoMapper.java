package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.mapper.sqlProvider.PhotoSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotoMapper {

    @Insert("""
            insert into t_photo
            (works_id, is_public)
            values(#{photo.worksId}, #{photo.isPublic})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addPhoto(@Param("photo") Photo photo);

    @Delete("delete from t_photo where id=#{id}")
    int deletePhotoById(@Param("id") Integer id);

    @UpdateProvider(value = PhotoSqlProvider.class, method = "updatePhotoById")
    int updatePhotoById(@Param("photo") Photo photo);

    @Select("select * from t_photo where id=#{id}")
    Photo inquirePhotoById(@Param("id") Integer id);

    @Select("select * from t_photo")
    List<Photo> inquireAllPhotos();
}
