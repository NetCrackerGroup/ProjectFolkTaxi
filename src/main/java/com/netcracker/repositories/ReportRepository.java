package com.netcracker.repositories;

import com.netcracker.entities.Report;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository extends AbstractRepository<Report> {


    public ReportRepository() {
        super(Report.class);
    }

}
