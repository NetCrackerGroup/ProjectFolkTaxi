package com.netcracker.controllers;


import com.netcracker.entities.Report;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import com.netcracker.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("reports")
//@CrossOrigin(origins="*", maxAge=3600)
public class ReportController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @GetMapping ("")
    String home ()
    {
        return "reports";
    }

    @GetMapping("/{id}")
    Report getReportById (@PathVariable(name = "id") String id){
        LOG.info("[ getReportById : {}", id);

        Report report = reportService.findReportById( Long.decode(id));

        return report;
    }

    @PostMapping("/create-report")
    Long createNewReport (@RequestParam(name = "userId") Long userId,
                          @RequestParam(name = "reportReason") String reportReason,
                          @RequestParam(name = "reportText") String reportText
                         ){

        Long reportId = reportService.createNewReport(userId, reportReason, reportText);

        return reportId;
    }

}
