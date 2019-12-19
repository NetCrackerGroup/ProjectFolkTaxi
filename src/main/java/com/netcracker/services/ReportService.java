package com.netcracker.services;


import com.netcracker.entities.Report;
import com.netcracker.entities.User;
import com.netcracker.repositories.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private ReportRepository reportRepository;


    public Report getReportById(Long reportId){

        Report report = reportRepository.find(reportId);

        return report;
    }

    public Long createNewReport(    String reportReason,
                                    String reportText,
                                    Boolean wasConsidered,
                                    Long moderatorId,
                                    User user)
    {
        LOG.debug("[ reportReason(fio : {}, reportText : {}, wasConsidered : {} , moderatorId : {} , moderatorId : {} "
                    , reportReason, reportText, wasConsidered, moderatorId);

        Report report = new Report( reportReason, reportText,
                                    wasConsidered, moderatorId, user
                );
        reportRepository.save(report);

        LOG.debug("] (reportId : {})", report.getReportId());
        return report.getReportId();
    }

   /* public List<Report> findAllReportOfModerator(Long moderatorId) {
        LOG.debug("find all report of moderator : {}" , moderatorId );

        return reportRepository.findAllReportOfModerator(moderatorId);
    }*/

}
