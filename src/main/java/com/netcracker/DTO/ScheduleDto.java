
package com.netcracker.DTO;

import java.util.Date;

public class ScheduleDto {
    private String timeOfJourney;
    private String scheduleDay;
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setTimeOfJourney(String timeOfJourney) {
        this.timeOfJourney = timeOfJourney;
    }

    public String getTimeOfJourney() {
        return timeOfJourney;
    }
}

