package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* 摄影作品表
* t_works
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Works {

    /**
    * 每幅作品的唯一标识 ID
    */
    private Integer id;
    /**
    * 作品上传者 ID
    */
    private Integer userId;
    /**
    * 物种表物种ID
    */
    private Integer speciesId;
    /**
    * 作品图片地址
    */
    private String imageUrl;
    /**
    * 作品是否公开展示，true 或 false
    */
    private Boolean isPublic;
    /**
    * 作品创建时间
    */
    private Date createTime;
    /**
    * 作品最近一次更新的时间
    */
    private Date updateTime;
    /**
    * 作品被查看的次数
    */
    private Integer viewCount;
    /**
    * 作品被点赞的次数
    */
    private Integer likeCount;
    /**
    * 作品被评论的次数
    */
    private Integer commentCount;
}
