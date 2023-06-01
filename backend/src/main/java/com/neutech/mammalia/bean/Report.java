package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户反馈表
 * t_report
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    /**
     * 举报唯一标识符
     */
    private Integer id;
    /**
     * 举报用户id
     */
    private String reporterName;
    /**
     * 被举报用户id
     */
    private String reporterPhone;
    /**
     * email
     */
    private String email;
    /**
     * 举报时间
     */
    private Date reportTime;
    /**
     * 举报原因
     */
    private String reportReason;
}
