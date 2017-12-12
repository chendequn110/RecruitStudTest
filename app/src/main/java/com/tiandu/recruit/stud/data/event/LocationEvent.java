package com.tiandu.recruit.stud.data.event;

/**
 * Created by Administrator on 2017/7/11.
 */

public class LocationEvent {
    private double longitude;
    private double latitude;

    public LocationEvent(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
