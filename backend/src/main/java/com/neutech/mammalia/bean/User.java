package com.neutech.mammalia.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 用户表
 * t_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 主键,用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像
     */
    @JsonProperty
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String gender;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否为管理员
     */
    private Boolean isAdmin;
}
