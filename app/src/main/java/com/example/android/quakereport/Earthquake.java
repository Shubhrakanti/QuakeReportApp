package com.example.android.quakereport;

/**
 * Created by giddu on 2/26/17.
 */

public class Earthquake {

    private double magnitude;
    private String direction;
    private String actualLocation;
    private long date;
    private static final String LOCATION_SEPARATOR = " of ";

    public Earthquake(double magnitude, long date, String location) {
        this.magnitude = magnitude;
        this.date = date;
        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            direction = parts[0] + LOCATION_SEPARATOR;
            actualLocation = parts[1];
        }
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getDate() {
        return date;
    }

    public String getDirection() {
        return direction;
    }

    public String getActualLocation() {
        return actualLocation;
    }
}
