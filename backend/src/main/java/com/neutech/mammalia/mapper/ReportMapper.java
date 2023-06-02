package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Insert("""
            insert into t_report
            (reporter_name, reporter_phone, email, report_reason)
            values(#{report.reporterName}, #{report.reporterPhone}, #{report.email}, #{report.reportReason})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addReport(@Param("report") Report report);

    @Delete("delete from t_report where id=#{id}")
    int deleteReportById(@Param("id") Integer id);

    @Update("""
            update t_report set
            reporter_name=#{report.reporterName},
            reporter_phone=#{report.reporterPhone},
            email=#{report.email},
            report_reason=#{report.reportReason}
            where id=#{report.id}
            """)
    int updateReportById(@Param("report") Report report);

    @Select("select * from t_report where id=#{id}")
    Report inquireReportById(@Param("id") Integer id);

    @Select("select * from t_report")
    List<Report> inquireAllReports();
}
