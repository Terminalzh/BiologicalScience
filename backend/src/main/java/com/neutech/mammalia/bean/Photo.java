package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 摄影作品表ID
     */
    private Works works;

    /**
     * 照片墙是否公开展示，true 或 false
     */
    private Boolean isPublic;

    /**
     * 照片墙被查看的次数
     */
    private Integer viewCount;

    /**
     * 照片墙被点赞的次数
     */
    private Integer likeCount;

    /**
     * 照片墙被评论的次数
     */
    private Integer commentCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 照片墙最近一次更新的时间
     */
    private Date updateTime;
}
