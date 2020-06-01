package com.example.android.quakereport;

public class Earthquake {
    private Double mMagnitude;
    private String mCity;
    private long mTimeInMilliseconds;
    private  String mUrl;

    public Earthquake(Double magnitude, String city, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mCity = city;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getCity() {
        return mCity;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
