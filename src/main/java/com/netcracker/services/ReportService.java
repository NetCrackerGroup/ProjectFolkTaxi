package com.netcracker.services;


import com.netcracker.entities.Report;
import com.netcracker.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {


    @Autowired
    private ReportRepository reportRepository;


    public Report getReport(Long reportId){

        Report report = reportRepository.find(reportId);

        return report;
    }

}
