package com.netcracker.Controllers;


import com.netcracker.entities.Report;
import com.netcracker.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

        Report report = reportService.findReportById( Long.decode(id));

        return report;
    }

}
