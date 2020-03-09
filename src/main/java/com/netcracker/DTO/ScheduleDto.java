package com.netcracker.DTO;

public class ScheduleDto {
    private String timeOfJourney;
    private String scheduleDay;

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
