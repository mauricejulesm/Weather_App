package com.julesmaurice.S1719024.mpd;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * <p>
 * <p>
 * <p>
 * <p>
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View view;

    static Weather weatherObj;
    public static ArrayList<Weather> weathers;



    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    /**
     * @param savedInstanceState the current instance
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        weathers = new ArrayList<>();


        // execute the process in the background operation
        new ProcessInBackground().execute("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123");

    }


    /**
     * a method to make a connection
     *
     * @param urlFeed the url of the online RSS FEED resource
     * @return returns the stream
     */

    public static InputStream getInputStream(URL urlFeed) {
        try {
            return urlFeed.openConnection().getInputStream();
        } catch (IOException ioe) {
            return null;
        }

    }


    /**
     * Class for using threads in the background
     * The inner class to process the async tasks in the background.
     */
    public class ProcessInBackground extends AsyncTask<String, Void, String> {

        //show a progress dialog
        ProgressDialog progressDialog = new ProgressDialog(ListFrag.this.getContext());
//        Exception exception;

        /**
         * Run after the async task is done.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading weather rss feeds...");
            progressDialog.show();
        }

        /**
         * @param strings takes the what you want to pass to the method that parses the data.
         * @return this exception if there is a error while parsing data.
         */
        @Override
        protected String doInBackground(String... strings) {

            String weatherFeeds = String.valueOf(parseXML(strings[0]));

            return weatherFeeds;
        }

        /**
         * @param s takes what was returned from the do in background method
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            recyclerView = view.findViewById(R.id.list);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ListFrag.this.getActivity());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new WeatherAdapter(ListFrag.this.getActivity(), R.layout.row_layout, weathers);
            recyclerView.setAdapter(adapter);


            progressDialog.dismiss();

//            onItemClicked(0);

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, feedsTitles);

//            lvRSS.setAdapter(adapter);
        }
    }


    public ArrayList<Weather> parseXML(String xmlUrl){

        try {
            URL url = new URL(xmlUrl);

            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            pullParserFactory.setNamespaceAware(false);

            XmlPullParser xpp = pullParserFactory.newPullParser();
            xpp.setInput(getInputStream(url), "UTF-8");

            //checking if we are inside the item property
            boolean insideItem = false;

            int eventType = xpp.getEventType();

            // looping over xml feeds' items
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    Log.i("Parsing Xml", xpp.getName());
                    //check  here if the tag is image to retrieve city name
//                        if (xpp.getName().equalsIgnoreCase("image")) {
//
//                        }

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                        weatherObj = new Weather();
                        weatherObj.setCity("Glasgow City");

                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem) {

                            String[] titleStrings = xpp.nextText().split(",");
                            StringBuilder longTempName = new StringBuilder(titleStrings[1]);
                            String realTemp = longTempName.substring(22, 33);

                            weatherObj.setMinTemp(realTemp);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (insideItem) {

                            String[] descStrings = xpp.nextText().split(",");
                            String windDirection, windSpeed, humidity, sunrise = "", sunset;

                            if (descStrings.length < 10) {
                                windDirection = descStrings[1];
                                windSpeed = descStrings[2];
                                humidity = descStrings[5];
//                                    sunrise = descStrings[9];
                                sunset = descStrings[8];
                            } else {
                                windDirection = descStrings[2];
                                windSpeed = descStrings[3];
                                humidity = descStrings[6];
                                sunrise = descStrings[9];
                                sunset = descStrings[10];
                            }

                            // setting the values of the weather object
                            weatherObj.setWindDirection(windDirection);
                            weatherObj.setWindSpeed(windSpeed);
                            weatherObj.setHumidity(humidity);
                            weatherObj.setSunRising(sunrise);
                            weatherObj.setSunSetting(sunset);

                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    Log.i("End tag", xpp.getName());
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
