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
        return 0;
    }

    @Override
    public int deleteReportById(Integer id) {
        return 0;
    }

    @Override
    public int updateReportById(Report report) {
        return 0;
    }

    @Override
    public Report inquireReportById(Integer id) {
        return null;
    }

    @Override
    public List<Report> inquireAllReport() {
        return null;
    }
}
