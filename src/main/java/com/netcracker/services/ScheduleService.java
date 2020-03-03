package com.netcracker.services;

import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleRepository.class);
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    public Schedule getScheduleByRoute(Long routeId) {
        Route route = routeRepository.findRouteByRouteId(routeId);
        Schedule schedule = scheduleRepository.findScheduleByRouteId(route);
        return schedule;
    }

}
