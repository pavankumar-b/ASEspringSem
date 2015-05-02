package com.example.pavan.goeasy;

/**
 * Created by pavan on 5/1/2015.
 */
public class TransportDetails {
private String busNo;
private String direction;
private String time;

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTime() {
        return time;
    }

    public String getBusNo() {
        return busNo;
    }

    public String getDirection() {
        return direction;
    }
}
