package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 轮播图表
 * t_banner
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 物种
     */
    private Species species;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
