package com.neutech.mammalia.service;

import com.neutech.mammalia.bean.Report;

import java.util.List;

public interface ReportService {

    int addReport(Report report);

    int deleteReportById(Integer id);

    int updateReportById(Report report);

    Report inquireReportById(Integer id);

    List<Report> inquireAllReport();

}
