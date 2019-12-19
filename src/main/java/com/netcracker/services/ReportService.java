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

}
