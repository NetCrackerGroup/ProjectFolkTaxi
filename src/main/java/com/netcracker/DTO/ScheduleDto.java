package com.netcracker.DTO;

public class ScheduleDto {
    private String time;
    public int[] selectedDays;

    public void setSelectedDays(int[] selectedDays) {
        this.selectedDays = selectedDays;
    }

    public int[] getSelectedDays() {
        return selectedDays;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
