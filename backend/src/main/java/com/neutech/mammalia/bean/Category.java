package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物种分类表
 * t_category
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 中文名
     */
    private Integer cName;
    /**
     * 拉丁文名
     */
    private Integer latinName;
    /**
     * 纲总数
     */
    private Integer subClassCount;
    /**
     * 目总数
     */
    private Integer orderCount;
    /**
     * 科总数
     */
    private Integer familyCount;
    /**
     * 属总数
     */
    private Integer genusCount;
    /**
     * 种总数
     */
    private Integer speciesCount;
    /**
     * 父级ID对应分类表的主键
     */
    private Integer parentId;
}
