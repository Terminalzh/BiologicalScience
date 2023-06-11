package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Report;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.Works;
import com.neutech.mammalia.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> addReport(@RequestBody Report report) {
        if (reportService.addReport(report) == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteReportById(@PathVariable Integer id) {
        if (reportService.deleteReportById(id) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateReportById(@PathVariable Integer id, @RequestBody Report report) {
        report.setId(id);
        if (reportService.updateReportById(report) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> inquireReportById(@PathVariable Integer id) {
        Report report = reportService.inquireReportById(id);
        if (report != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, report));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Response> inquireAllReport(Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Report> reports = reportService.inquireAllReport();
        PageInfo<Report> reportPageInfo = new PageInfo<>(reports);
        if (reports.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, reportPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));

    }
}
