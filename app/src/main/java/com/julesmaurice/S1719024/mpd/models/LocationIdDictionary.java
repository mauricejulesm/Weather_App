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
        iDsDictionary.put("Glasgow", "2648579");
        iDsDictionary.put("London", "2643743");
        iDsDictionary.put("NY", "5128581");
        iDsDictionary.put("Oman", "287286");
        iDsDictionary.put("MU", "934154");
        iDsDictionary.put("Bang", "1185241");
        iDsDictionary.put("Kigali", "202061");

        return LocationIdDictionary.iDsDictionary.get(cityName).trim();
    }


}
