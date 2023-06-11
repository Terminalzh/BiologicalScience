package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCount {
    /**
     * 对应物种分类表的id
     */
    private Integer id;
    /**
     * 亚纲总数
     */
    private Integer subClass;
    /**
     * 目总数
     */
    private Integer orderCount;
    /**
     * 科总数
     */
    private Integer family;
    /**
     * 数总数
     */
    private Integer genus;
    /**
     * 种总数
     */
    private Integer species;
    /**
     * 父ID
     */
    private Integer parentId;
    /**
     * 分类继承关系
     */
    private String categorizedInheritance;
}
