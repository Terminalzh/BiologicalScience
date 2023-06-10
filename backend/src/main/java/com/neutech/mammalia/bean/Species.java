package com.neutech.mammalia.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物种
 * t_species
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 物种的中文名称
     */
    @JsonProperty
    private String cName;
    /**
     * 物种的拉丁文正式名
     */
    private String latinName;
    /**
     * 该物种所属的类别
     */
    private Integer genusId;
    /**
     * 物种简介
     */
    private String briefIntroduction;
    /**
     * 物种详情
     */
    private String detailIntroduction;
    /**
     * 是否推荐.true:推荐false:不推荐.每日24点重新生成推荐内容
     */
    private Boolean recommend;
    /**
     * 保护级别. 1:国1; 2:国2; 3:三有; 0:非保护动物;
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 上次更新时间
     */
    private Date updateTime;
    /**
     * 物种照片地址
     */
    private String pictureUrl;
    /**
     * better
     */
    private String betterUrl;
    /**
     * 继承关系
     */
    private Map<Integer, List<String>> inheritance;
}
