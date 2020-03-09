package com.netcracker.services;

import com.netcracker.DTO.ScheduleDto;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ScheduleMapper extends AbstractMapper<Schedule, ScheduleDto> {

    public ScheduleMapper() {
        super(Schedule.class, ScheduleDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ScheduleDto.class, Schedule.class).addMapping(ScheduleDto::getScheduleDay, Schedule::setScheduleDay).setPostConverter(
                context -> {
                    LOG.debug("ScheduleDTO Convert");
                    ScheduleDto scheduleDto = context.getSource();
                    Schedule schedule = context.getDestination();
                    schedule.setScheduleDay(Integer.parseInt(scheduleDto.getScheduleDay(), 2));
                    return schedule;
                }
        ).addMapping(ScheduleDto::getTimeOfJourney, Schedule::setTimeOfJourney).setPostConverter(
                context -> {
                    LOG.debug("ScheduleDTO Convert");
                    ScheduleDto scheduleDto = context.getSource();
                    Schedule schedule = context.getDestination();
                    SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
                    try {
                        schedule.setTimeOfJourney(sfd.parse(scheduleDto.getTimeOfJourney()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return schedule;
                }
        );
        mapper.createTypeMap(String.class, Date.class).setPostConverter(context -> {
            LOG.debug("String in Date");
            Date date = context.getDestination();
            String strDate = context.getSource();
            SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
            try {
                date = sfd.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return context.getDestination();
        });
        mapper.createTypeMap(String.class, Integer.class).setPostConverter(context -> {
            LOG.debug("String in Integer");
            Integer integer = context.getDestination();
            String string = context.getSource();
            integer = Integer.parseInt(string, 2);
            return context.getDestination();
        });
    }

}
