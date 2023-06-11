package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Photo;
import com.neutech.mammalia.mapper.sqlProvider.PhotoSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotoMapper {

    @Insert("""
            insert into t_photo
            (species_id)
            values(#{photo.works.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addPhoto(@Param("photo") Photo photo);

    @Delete("delete from t_photo where id=#{id}")
    int deletePhotoById(@Param("id") Integer id);

    @Delete("delete from t_photo where species_id = #{speciesId}")
    int deletePhotoBySpeciesId(@Param("speciesId") Integer speciesId);

    @UpdateProvider(value = PhotoSqlProvider.class, method = "updatePhotoById")
    int updatePhotoById(@Param("photo") Photo photo);

    @Results(id = "photoResultMapping", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "species_id", property = "species",
                    one = @One(select = "com.neutech.mammalia.mapper.SpeciesMapper.inquireSpeciesById")),
            @Result(column = "view_count", property = "viewCount"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "comment_count", property = "commentCount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "updateTime", property = "updateTime")
    })
    @Select("select * from t_photo where id=#{id}")
    Photo inquirePhotoById(@Param("id") Integer id);

    @ResultMap(value = "photoResultMapping")
    @Select("select * from t_photo")
    List<Photo> inquireAllPhotos();
}
