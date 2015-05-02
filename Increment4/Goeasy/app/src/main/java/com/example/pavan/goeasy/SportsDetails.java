package com.example.pavan.goeasy;

/**
 * Created by pavan on 5/1/2015.
 */
public class SportsDetails {
        private String Date;
        private String event;
        private String location;
        private String time;

    public void setDate(String date) {
        Date = date;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return Date;
    }

    public String getEvent() {
        return event;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}
