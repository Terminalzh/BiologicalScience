package com.neutech.mammalia.controller;

import com.neutech.mammalia.bean.Report;
import com.neutech.mammalia.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Resource
    private ReportService reportService;

    @PostMapping
    public Map<String, Object> addReport(@RequestBody Report report) {
        Map<String, Object> map = new HashMap<>();
        if (reportService.addReport(report) == 1) {
            map.put("code", HttpStatus.CREATED.value());
            map.put("message", HttpStatus.CREATED.getReasonPhrase());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Object> deleteReportById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (reportService.deleteReportById(id) == 1) {
            map.put("code", HttpStatus.NO_CONTENT.value());
            map.put("message", HttpStatus.NO_CONTENT.value());
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @PutMapping(value = "/{id}")
    public Map<String, Object> updateReportById(@PathVariable Integer id, @RequestBody Report report) {
        Map<String, Object> map = new HashMap<>();
        report.setId(id);
        if (reportService.updateReportById(report) == 1) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> inquireReportById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Report report = reportService.inquireReportById(id);
        if (report != null) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", report);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> inquireAllReport() {
        Map<String, Object> map = new HashMap<>();
        List<Report> reports = reportService.inquireAllReport();
        if (reports.size() > 0) {
            map.put("code", HttpStatus.OK.value());
            map.put("message", HttpStatus.OK.value());
            map.put("data", reports);
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return map;
    }

}
