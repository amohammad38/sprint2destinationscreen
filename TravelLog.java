package com.example.sprint2portion;

public class TravelLog {

    private String location;
    private String startDate;
    private String endDate;
    private String duration;

    public TravelLog(String location, String startDate, String endDate, String duration) {
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}