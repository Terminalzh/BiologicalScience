package com.neutech.mammalia.mapper.sqlProvider;

import com.neutech.mammalia.bean.Photo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

public class PhotoSqlProvider {
    public static String updatePhotoById(Photo photo) {
        SQL sql = new SQL();
        sql.UPDATE("t_photo");
        if (photo.getSpeciesId() != null)
            sql.SET("species_id", "photo.speciesId");
        if (photo.getWorksId() != null)
            sql.SET("works_id", "photo.worksId");
        if (photo.getIsPublic() != null)
            sql.SET("is_public", "photo.isPublic");
        if (photo.getViewCount() != null)
            sql.SET("view_count", "photo.viewCount");
        if (photo.getLikeCount() != null)
            sql.SET("like_count", "photo.likeCount");
        if (photo.getCommentCount() != null)
            sql.SET("comment_count", "photo.commentCount");
        photo.setUpdateTime(new Date());
        sql.SET("update_time", "photo.updateTime");
        sql.WHERE("id", "photo.id");
        return sql.toString();
    }
}
