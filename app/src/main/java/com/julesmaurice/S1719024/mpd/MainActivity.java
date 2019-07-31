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


        // setting up the views
        tvDTemp = findViewById(R.id.tvDetailTemp);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvCity = findViewById(R.id.tvDetailCity);


//        // execute the process in the background operation
//        new ProcessInBackground().execute();


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


        weathers = ListFrag.weathers;
        // show the first item on the list
        if (weathers != null && weathers.size() != 0) {
            ListFrag.progressDialog.dismiss();

            onItemClicked(0);
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
    }
}
