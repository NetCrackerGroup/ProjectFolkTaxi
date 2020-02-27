package com.netcracker.services;

import com.netcracker.DTO.ScheduleDto;
import com.netcracker.entities.Schedule;
import org.springframework.stereotype.Component;


@Component
public class ScheduleMapper extends AbstractMapper<Schedule, ScheduleDto> {

    public ScheduleMapper() {
        super(Schedule.class, ScheduleDto.class);
    }

}
