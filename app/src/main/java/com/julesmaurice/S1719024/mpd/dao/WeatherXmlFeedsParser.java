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

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */
public class WeatherXmlFeedsParser {

    private static String baseFeedsUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";

    private static Weather weatherObj;

    public WeatherXmlFeedsParser() {
    }

    /**
     * a method to make a connection
     *
     * @param urlFeed the url of the online RSS FEED resource
     * @return returns the stream
     */

    private static InputStream getRssFeedStream(URL urlFeed) {
        try {
            return urlFeed.openConnection().getInputStream();
        } catch (IOException ioe) {
            return null;
        }

    }

    /**
     * @param currentCityID the city for which the feeds are for
     * @return returns a list of weather objects
     */
    public static ArrayList<Weather> parseWeatherXML(String currentCityID) {

        ArrayList<Weather> weathers = new ArrayList<>();
        //boolean to check if we are inside the item property
        boolean insideItemTAG = false;
        String currentCityName = "";

        try {
            // the full url
            URL url = new URL(baseFeedsUrl + currentCityID);

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(false);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(getRssFeedStream(url), "UTF-8");

            int eventType = xmlPullParser.getEventType();

            // looping over xml feeds' items
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String currentTagName = xmlPullParser.getName();

                if (eventType == XmlPullParser.START_TAG) {
                    Log.i("Parsing Xml", "At start tag");

                    // retrieving the current city name
                    if (currentTagName.equalsIgnoreCase("title") && !insideItemTAG) {
                        currentCityName = xmlPullParser.nextText().split("for")[1].trim();
                    }
                    if (currentTagName.equalsIgnoreCase("item")) {
                        insideItemTAG = true;
                        weatherObj = new Weather();
                        weatherObj.setCity(currentCityName);

                    } else if (currentTagName.equalsIgnoreCase("title")) {
                        if (insideItemTAG) {
                            String[] titleStrings = xmlPullParser.nextText().split(",");

                            weatherObj.setDayOfTheWeek(titleStrings[0].trim());
                            weatherObj.setMinTemp(titleStrings[1].split(":")[1].substring(1, 6).trim());
                        }
                    } else if (currentTagName.equalsIgnoreCase("description")) {
                        if (insideItemTAG) {

                            String[] descStrings = xmlPullParser.nextText().split(",");
                            String windDirection, windSpeed, humidity, sunrise = "", sunset, visibility, pressure, maxTemp = "";

                            // for days that don't have maximum temperature and sunset
                            if (descStrings.length < 10) {
                                windDirection = descStrings[1].trim();
                                windSpeed = descStrings[2].trim();
                                visibility = descStrings[3].trim();
                                pressure = descStrings[4].trim();
                                humidity = descStrings[5].trim();
                                sunset = descStrings[8].trim();
                            } else {
                                maxTemp = descStrings[0].substring(21, 25).trim();
                                windDirection = descStrings[2].trim();
                                windSpeed = descStrings[3].trim();
                                visibility = descStrings[4].trim();
                                pressure = descStrings[5].trim();
                                humidity = descStrings[6].trim();
                                sunrise = descStrings[9].trim();
                                sunset = descStrings[10].trim();
                            }
                            // setting the values of the weather object
                            weatherObj.setWindDirection(windDirection);
                            weatherObj.setWindSpeed(windSpeed);
                            weatherObj.setHumidity(humidity);
                            weatherObj.setVisibility(visibility);
                            weatherObj.setPressure(pressure);
                            weatherObj.setMaxTemp(maxTemp);
                            weatherObj.setSunRising(sunrise);
                            weatherObj.setSunSetting(sunset);
                        }
                    } else if (currentTagName.equalsIgnoreCase("pubDate")) {
                        if (insideItemTAG) {

                            String pubDate = "Date: "+xmlPullParser.nextText().substring(0, 16).trim();
                            weatherObj.setDateOfForecast(pubDate);
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && currentTagName.equalsIgnoreCase("item")) {
                    Log.i("End tag", currentTagName);
                    if (weatherObj != null) {
                        weathers.add(weatherObj);
                    }
                    insideItemTAG = false;
                }
                // help in going to the next item on the xml file
                eventType = xmlPullParser.next();
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
