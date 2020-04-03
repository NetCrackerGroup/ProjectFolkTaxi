package com.netcracker.repositories;

import com.netcracker.entities.City;
import com.netcracker.entities.Report;
import com.netcracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {


}
