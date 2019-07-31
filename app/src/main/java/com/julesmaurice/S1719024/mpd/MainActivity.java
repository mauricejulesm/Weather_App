package com.julesmaurice.S1719024.mpd;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked{

    public static ArrayList<Weather> weathers;
    static Weather weatherObj;

    TextView tvDTemp, tvDDesc, tvCity;

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.Fragment listFrag;
    android.support.v4.app.Fragment weatherDetailsFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weathers = new ArrayList<>();

        // setting up the views
        tvDTemp = findViewById(R.id.tvDetailTemp);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvCity = findViewById(R.id.tvDetailCity);


        // execute the process in the background operation
        new ProcessInBackground().execute();


        // managing the showing and hiding of the fragments
        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.listFrag);
        weatherDetailsFrag = fragmentManager.findFragmentById(R.id.weatherDetailsFrag);

        fragmentManager.beginTransaction()
                .show(listFrag)
                .show(weatherDetailsFrag)
                .commit();


//        btnOwnerInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager.beginTransaction()
//                        .hide(carInfoFrag)
//                        .show(ownerInfoFrag)
//                        .commit();
//            }
//        });
//        btnCarInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager.beginTransaction()
//                        .show(carInfoFrag)
//                        .hide(ownerInfoFrag)
//                        .commit();
//            }
//        });


        // show the first item on the list
//        onItemClicked(0);

    }


    /**
     * a method to make a connection
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
     *  Class for using threads in the background
     *  The inner class to process the async tasks in the background.
     */
    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {

        //show a progress dialog
//        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception;

        /**
         * Run after the async task is done.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            progressDialog.setMessage("Loading weather rss feeds...");
//            progressDialog.show();
        }

        /**
         * @param integers takes the what you want to pass to the method that parses the data.
         * @return this exception if there is a error while parsing data.
         */
        @Override
        protected Exception doInBackground(Integer... integers) {

            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123");

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
                                String windDirection, windSpeed, humidity, sunrise ="", sunset;

                                if (descStrings.length < 10){
                                    windDirection = descStrings[1];
                                    windSpeed = descStrings[2];
                                    humidity = descStrings[5];
//                                    sunrise = descStrings[9];
                                    sunset = descStrings[8];
                                }else {
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
                        if (weatherObj != null){
                            weathers.add(weatherObj);
                        }
                        insideItem = false;
                    }

                    // help in going to the next item on the xml file
                    eventType = xpp.next();
                }


            } catch (MalformedURLException mex) {
                exception = mex;
            } catch (XmlPullParserException xml) {
                exception = xml;
            } catch (IOException io) {
                exception = io;
            }


            return exception;
        }

        /**
         * @param s takes what was returned from the do in background method
         */
        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
//            progressDialog.dismiss();

            onItemClicked(0);

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, feedsTitles);

//            lvRSS.setAdapter(adapter);
        }
    }


    /**
     * the method to set the data to the views of the detail view
     * @param index the position at which the selected card is is
     */
    @Override
    public void onItemClicked(int index) {
        tvDTemp.setText(weathers.get(index).getMinTemp());
        tvDDesc.setText(weathers.get(index).getWindDirection());
        tvCity.setText(weathers.get(index).getCity());

//        if(WeatherDAO.weathers.get(index).getMake().equals("Volkswagen")){
//            ivMake.setImageResource(R.drawable.volkswagen);
//        }else if(WeatherDAO.weathers.get(index).getMake().equals("Nissa")){
//            ivMake.setImageResource(R.drawable.nissan);
//        }else {
//            ivMake.setImageResource(R.drawable.mercedes);
//        }
    }
   }
