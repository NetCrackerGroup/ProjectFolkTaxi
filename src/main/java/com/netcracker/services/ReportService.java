package com.netcracker.services;


import com.netcracker.entities.City;
import com.netcracker.entities.Report;
import com.netcracker.entities.User;
import com.netcracker.repositories.ReportRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    public Report findReportById(Long id){

        Optional<Report> report = reportRepository.findById(id);

        return report.isPresent() ? report.get() : null;
    }

    public Long createNewReport(Long userId,
                                String reportReason,
                                String reportText
                                ){

        Optional<User> user = userRepository.findById(userId);

        Report newReport = new Report(user.get(), reportReason, reportText);

        reportRepository.save(newReport);

        return newReport.getReportId();
    }


}

