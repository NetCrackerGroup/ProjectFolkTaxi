package com.netcracker.DTO;

public class ScheduleDto {
    private String timeOfJourney;
    public String selectedDays;

    public void setSelectedDays(String selectedDays) {
        this.selectedDays = selectedDays;
    }

    public String getSelectedDays() {
        return selectedDays;
    }

    public void setTimeOfJourney(String timeOfJourney) {
        this.timeOfJourney = timeOfJourney;
    }

    public String getTimeOfJourney() {
        return timeOfJourney;
    }
}
