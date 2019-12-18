package com.netcracker.Controllers;


import com.netcracker.entities.Report;
import com.netcracker.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("reports")
public class ReportController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

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

        Report report = reportService.getReportById( Long.decode(id));

        return report;
    }

    @PostMapping("")
    public Long createNewUser(  @RequestParam  String reportReason,
                                @RequestParam  String reportText,
                                @RequestParam  Boolean wasConsidered,
                                @RequestParam Long moderatorId
    )
    {
        LOG.debug("[ createReport(reportReason : {}, reportText : {}, wasConsidered : {} , moderatorId",
                reportReason, reportText, wasConsidered);

        // Tyt
        Long userId = null;

        LOG.debug("] (userId : {})", userId);
        return userId;
    }


    @GetMapping("/moderator/{id}")
    List<Report> findAllReportOfModerator (@PathVariable(name = "id") String id){
        LOG.info("[ getReportById : {}", id);

        List<Report> reports = reportService.findAllReportOfModerator(Long.decode(id));

        return reports;
    }
}
