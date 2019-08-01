package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked {

    public static ArrayList<Weather> weathers;

    TextView tvDTemp, tvDDesc, tvCity;

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.Fragment listFrag;
    android.support.v4.app.Fragment weatherDetailsFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //setting up the custom toolbar
//        Toolbar toolbar = findViewById(R.id.app_bar);
//        setSupportActionBar(toolbar);

        // setting up the views
        tvDTemp = findViewById(R.id.tvDetailTemp);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvCity = findViewById(R.id.tvDetailCity);


//        // execute the process in the background operation
//        new ProcessInBackground().execute();

//        String intent = getIntent().getExtras();




        // managing fragments

        //showing weather list only in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(fragmentManager.findFragmentById(R.id.weatherDetailsFrag))
                    .show(fragmentManager.findFragmentById(R.id.listFrag))
                    .commit();
        }

        //showing weather list only in portrait mode
        if (findViewById(R.id.layout_land) != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.weatherDetailsFrag))
                    .show(fragmentManager.findFragmentById(R.id.listFrag))
                    .commit();
        }



        weathers = ListFrag.weathers;
        // show the first item on the list
        if (weathers != null && weathers.size() != 0) {
            ListFrag.progressDialog.dismiss();

//            onItemClicked(0);
        }

    }


    /**
     * the method to set the data to the views of the detail view
     *
     * @param index the position at which the selected card is is
     */
    @Override
    public void onItemClicked(int index) {

        tvDTemp.setText(ListFrag.weathers.get(index).getMinTemp());
        tvDDesc.setText(ListFrag.weathers.get(index).getWindDirection());
        tvCity.setText(ListFrag.weathers.get(index).getCity());

//        if(WeatherDAO.weathers.get(index).getMake().equals("Volkswagen")){
//            ivMake.setImageResource(R.drawable.volkswagen);
//        }else if(WeatherDAO.weathers.get(index).getMake().equals("Nissa")){
//            ivMake.setImageResource(R.drawable.nissan);
//        }else {
//            ivMake.setImageResource(R.drawable.mercedes);
//        }

        //switch layouts
        if (findViewById(R.id.layout_portrait) != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.weatherDetailsFrag))
                    .hide(fragmentManager.findFragmentById(R.id.listFrag))
                    .addToBackStack(null)
                    .commit();
        }


    }


//    /**
//     * @param menu the menu layout
//     * @return options of the menu to the activity
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
}
