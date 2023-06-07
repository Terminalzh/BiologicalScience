package com.neutech.mammalia.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonProperty("cName")
    private String cName;
    /**
     * 拉丁文名
     */
    private String latinName;
    /**
     * 父级ID对应分类表的主键
     */
    private Integer parentId;
}
