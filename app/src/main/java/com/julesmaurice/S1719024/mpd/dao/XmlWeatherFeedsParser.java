package com.julesmaurice.S1719024.mpd.dao;

import android.util.Log;

import com.julesmaurice.S1719024.mpd.models.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */
public class XmlWeatherFeedsParser {

    private static String baseFeedsUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private static String[] locationIDs = {"5128581", "2648579", "2643743", "287286", "1185241", "934154", "202061"};

    private static Weather weatherObj;

    public XmlWeatherFeedsParser() {
//        citiesWeather = new HashMap<>();
    }

    /**
     * a method to make a connection
     *
     * @param urlFeed the url of the online RSS FEED resource
     * @return returns the stream
     */

    private static InputStream getInputStream(URL urlFeed) {
        try {
            return urlFeed.openConnection().getInputStream();
        } catch (IOException ioe) {
            return null;
        }

    }

    public static ArrayList<Weather> parseXML(String currentCityID) {


        ArrayList<Weather> weathers = null;
        //boolean to check if we are inside the item property
        boolean insideItem = false;
        String currentCityName = "";

        weathers = new ArrayList<>();


            try {
                URL url = new URL(baseFeedsUrl + currentCityID);

                XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
                pullParserFactory.setNamespaceAware(false);

                XmlPullParser xpp = pullParserFactory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF-8");


                int eventType = xpp.getEventType();

                // looping over xml feeds' items
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String currentTagName = xpp.getName();

                    if (eventType == XmlPullParser.START_TAG) {
                        Log.i("Parsing Xml", currentTagName);
                        //check  here if the tag is image to retrieve city name

                        if (currentTagName.equalsIgnoreCase("title") && !insideItem) {
                            currentCityName = xpp.nextText().split("for")[1].trim();
                        }

                        if (currentTagName.equalsIgnoreCase("item")) {
                            insideItem = true;
                            weatherObj = new Weather();
                            weatherObj.setCity(currentCityName);

                        } else if (currentTagName.equalsIgnoreCase("title")) {
                            if (insideItem) {

                                String[] titleStrings = xpp.nextText().split(",");
                                StringBuilder longTempName = new StringBuilder(titleStrings[1]);
                                String realTemp = longTempName.substring(22, 33).trim();

                                weatherObj.setDatyOfTheWeek(titleStrings[0]);
                                weatherObj.setMinTemp(realTemp);
                            }
                        } else if (currentTagName.equalsIgnoreCase("description")) {
                            if (insideItem) {

                                String[] descStrings = xpp.nextText().split(",");
                                String windDirection, windSpeed, humidity, sunrise = "", sunset;

                                if (descStrings.length < 10) {
                                    windDirection = descStrings[1].trim();
                                    windSpeed = descStrings[2].trim();
                                    humidity = descStrings[5].trim();
//                                    sunrise = descStrings[9];
                                    sunset = descStrings[8].trim();
                                } else {
                                    windDirection = descStrings[2].trim();
                                    windSpeed = descStrings[3].trim();
                                    humidity = descStrings[6].trim();
                                    sunrise = descStrings[9].trim();
                                    sunset = descStrings[10].trim();
                                }

                                // setting the values of the weather object
                                weatherObj.setWindDirection(windDirection);
                                weatherObj.setWindSpeed(windSpeed);
                                weatherObj.setHumidity(humidity);
                                weatherObj.setSunRising(sunrise);
                                weatherObj.setSunSetting(sunset);

                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && currentTagName.equalsIgnoreCase("item")) {
                        Log.i("End tag", currentTagName);
                        if (weatherObj != null) {
                            weathers.add(weatherObj);
                        }
                        insideItem = false;
                    }

                    // help in going to the next item on the xml file
                    eventType = xpp.next();
                }

            } catch (MalformedURLException mex) {
                Log.e("Malformed Url", mex.getMessage());
            } catch (XmlPullParserException xml_ex) {
                Log.e("XMLPull", xml_ex.getMessage());
            } catch (IOException io) {
                Log.e("IOE on parsing xml", io.getMessage());
            }

        return weathers;
    }

}
