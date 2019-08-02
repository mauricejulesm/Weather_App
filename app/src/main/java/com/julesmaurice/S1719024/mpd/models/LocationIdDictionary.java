package com.julesmaurice.S1719024.mpd.models;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */
public class LocationIdDictionary {

    // Initializing a Dictionary
    private static Map<String, String> iDsDictionary = new HashMap<>();

    public static String getIDsDictionary(String cityName) {

        // put cities id
        iDsDictionary.put("Glasgow, GB", "2648579");
        iDsDictionary.put("London, GB", "2643743");
        iDsDictionary.put("New York, US", "5128581");
        iDsDictionary.put("Muscat, OM", "287286");
        iDsDictionary.put("Port Louis, MU", "934154");
        iDsDictionary.put("Dhaka, BD", "1185241");
        iDsDictionary.put("Kigali, RW", "202061");

        return LocationIdDictionary.iDsDictionary.get(cityName).trim();
    }


}
