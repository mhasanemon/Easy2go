package com.example.emon.splashscreen;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Emon on 7/27/2017.
 */

public class Direction_attributes {
    private String distance;
    private String duration;
    private String start_address;
    private String end_address;
    private LatLng source;
    private LatLng destination;

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LatLng getSource() {
        return source;
    }

    public void setSource(LatLng source) {
        this.source = source;
    }

    public LatLng getDestination() {
        return destination;
    }

    public void setDestination(LatLng destination) {
        this.destination = destination;
    }
}
