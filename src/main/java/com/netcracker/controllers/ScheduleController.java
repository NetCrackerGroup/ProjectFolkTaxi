package com.netcracker.controllers;

import com.netcracker.DTO.ScheduleDto;
import com.netcracker.entities.Schedule;
import com.netcracker.services.ScheduleMapper;
import com.netcracker.services.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
    @Autowired
    public ScheduleService scheduleService;

    @Autowired
    public ScheduleMapper scheduleMapper;
    @GetMapping("/route/{id}")
    public ScheduleDto getScheduleByRoute(@PathVariable(value = "id") Long routeId) {
        LOG.info("getScheduleByRoute : id{}", routeId);
        Schedule schedule = scheduleService.getScheduleByRoute(routeId);
        ScheduleDto scheduleDto = scheduleMapper.toDto(schedule);
        return scheduleDto;

    }
}
