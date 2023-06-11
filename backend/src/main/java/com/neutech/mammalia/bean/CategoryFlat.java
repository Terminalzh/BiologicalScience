package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFlat {
    private String id;
    private String parent;
    private String name;
    private Integer value;
    private Integer level;
}
