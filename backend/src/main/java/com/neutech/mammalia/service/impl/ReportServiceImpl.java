package com.neutech.mammalia.service.impl;

import com.neutech.mammalia.bean.Report;
import com.neutech.mammalia.mapper.ReportMapper;
import com.neutech.mammalia.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Override
    public int addReport(Report report) {
        return reportMapper.addReport(report);
    }

    @Override
    public int deleteReportById(Integer id) {
        return reportMapper.deleteReportById(id);
    }

    @Override
    public int updateReportById(Report report) {
        return reportMapper.updateReportById(report);
    }

    @Override
    public Report inquireReportById(Integer id) {
        return reportMapper.inquireReportById(id);
    }

    @Override
    public List<Report> inquireAllReport() {
        return reportMapper.inquireAllReports();
    }
}
