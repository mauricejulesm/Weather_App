package com.julesmaurice.S1719024.mpd.models;

public class Weather {
    private String city;
    private String description;
    private String degree_celsius;
    private String windSpeed;


    public Weather(String city, String degree_celsius, String description, String windSpeed) {
        this.city = city;
        this.description = description;
        this.degree_celsius = degree_celsius;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegree_celsius() {
        return degree_celsius;
    }

    public void setDegree_celsius(String degree_celsius) {
        this.degree_celsius = degree_celsius;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
