package com.example.android.quakereport;

/**
 * Created by mac on 29/07/17.
 */

public class Earthquake {
    private double mag;
    private String city;
    private long date;



    public Earthquake(double mag, String city, Long date) {
        this.mag = mag;
        this.city = city;
        this.date = date;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
