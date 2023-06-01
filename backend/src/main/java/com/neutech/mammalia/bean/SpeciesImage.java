package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesImage {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 物种id
     */
    private Integer speciesId;
    /**
     * 图片路径
     */
    private String path;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
