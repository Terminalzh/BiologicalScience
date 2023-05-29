package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Insert("""
            insert into t_report
            (reporter_user_id, reported_user_id, report_reason, report_time)
            values (#{reporterUserId}, #{reportedUserId}, #{reportReason}, #{reportTime});
            """)
    int addReport(Report report);

    @Delete("delete from t_report where id = #{id};")
    int deleteReportById(Integer id);

    @Update("""
            update t_report set
            reporter_user_id = #{reporterUserId},
            reported_user_id = #{reportedUserId},
            report_reason = #{reportReason},
            report_time = #{reportTime}
            where id = #{id};
            """)
    int updateReportById(Report report);

    @Select("select * from t_report where id = #{id}")
    Report inquireReportById(Integer id);

    @Select("select * from t_report")
    List<Report> inquireAllReport();

}
