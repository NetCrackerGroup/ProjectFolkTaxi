package com.netcracker.Controllers;


import com.netcracker.entities.Report;
import com.netcracker.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping ("")
    String home ()
    {
        return "reports";
    }

    @GetMapping("/{id}")
    Report getReport (@PathVariable(name = "id") String id){

        Report report = reportService.getReport( Long.decode(id));

        return report;
    }

}
