package com.netcracker.repositories;

import com.netcracker.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAllReportOfModerator(Long moderatorId);
}
