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
    public Response addReport(@RequestBody Report report) {
        Response response = new Response();
        if (reportService.addReport(report) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteReportById(@PathVariable Integer id) {
        Response response = new Response();
        if (reportService.deleteReportById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateReportById(@PathVariable Integer id, @RequestBody Report report) {
        Response response = new Response();
        report.setId(id);
        if (reportService.updateReportById(report) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response inquireReportById(@PathVariable Integer id) {
        Response response = new Response();
        Report report = reportService.inquireReportById(id);
        if (report != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(report);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllReport(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Report> reports = reportService.inquireAllReport();
        PageInfo<Report> reportPageInfo = new PageInfo<>(reports);
        if (reports.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(reportPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

}
