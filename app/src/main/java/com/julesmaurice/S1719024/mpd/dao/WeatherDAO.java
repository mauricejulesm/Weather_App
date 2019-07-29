package com.julesmaurice.S1719024.mpd.dao;

import android.app.Application;

import com.julesmaurice.S1719024.mpd.models.Weather;

import java.util.ArrayList;

public class WeatherDAO extends Application {
    public  static ArrayList<Weather> weathers;
    @Override
    public void onCreate() {
        super.onCreate();

        weathers = new ArrayList<>();
        weathers.add(new Weather("Glasgow", "21°C", "Weather information in Glasgow", "3 mph"));
        weathers.add(new Weather("Manchester", "10°C", "Weather information in Manchester", "4 mph"));
        weathers.add(new Weather("Port Louis", "-3°C", "Weather information in Port Louis", "34 mph"));
        weathers.add(new Weather("Kigali", "67°C", "Weather information in Kigali", "2 mph"));
        weathers.add(new Weather("New York", "40°C", "Weather information in New York", "76 mph"));
        weathers.add(new Weather("Paris", "23°C", "Weather information in Paris", "56 mph"));
    }
}
