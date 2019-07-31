package com.julesmaurice.S1719024.mpd.models;
/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 *

 * the model for the weather object
 */

public class Weather {
    private String city;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private String time;
    private String windDirection;
    private String sunSetting;
    private String sunRising;
    private String windSpeed;

    private String datyOfTheWeek;
    private String dateOfForecast;
    private String rainLikelihood;

    public Weather() {
        super();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getSunSetting() {
        return sunSetting;
    }

    public void setSunSetting(String sunSetting) {
        this.sunSetting = sunSetting;
    }

    public String getSunRising() {
        return sunRising;
    }

    public void setSunRising(String sunRising) {
        this.sunRising = sunRising;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDatyOfTheWeek() {
        return datyOfTheWeek;
    }

    public void setDatyOfTheWeek(String datyOfTheWeek) {
        this.datyOfTheWeek = datyOfTheWeek;
    }

    public String getDateOfForecast() {
        return dateOfForecast;
    }

    public void setDateOfForecast(String dateOfForecast) {
        this.dateOfForecast = dateOfForecast;
    }

    public String getRainLikelihood() {
        return rainLikelihood;
    }

    public void setRainLikelihood(String rainLikelihood) {
        this.rainLikelihood = rainLikelihood;
    }
}
