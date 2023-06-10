package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Data1 {
    /**
     * 主键
     */
    private Integer imageId;
    /**
     * 路径
     */
    private String path;
    /**
     * 物种id
     */
    private Integer speciesId;

}
