package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.julesmaurice.S1719024.mpd.models.LocationIdDictionary;
import com.julesmaurice.S1719024.mpd.models.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked {

    public static ArrayList<Weather> weathers;

    TextView tvDTemp, tvDDesc, tvCity;
    FloatingActionButton nextBtn, prevBtn;

    ArrayList<String> idsList = new ArrayList<>();

    String ids[] = {"2648579", "287286", "2643743", "5128581", "934154", "1185241", "202061"};

    int currentIndex = 0;


    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.Fragment listFrag;
    android.support.v4.app.Fragment weatherDetailsFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idsList.addAll(Arrays.asList(ids));

        // setting up the views
        tvDTemp = findViewById(R.id.tvDetailTemp);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvCity = findViewById(R.id.tvDetailCity);
        nextBtn = findViewById(R.id.NextButton);
        prevBtn = findViewById(R.id.PrevButton);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Now showing next city", Toast.LENGTH_SHORT).show();

                String currentCityName = ListFrag.weathers.get(0).getCity();

                currentIndex =+ idsList.indexOf(LocationIdDictionary.getIDsDictionary(currentCityName));

                if (currentIndex + 1 == idsList.size()) {
                    currentIndex = -1;
                }
                ListFrag.newInstance(ids[currentIndex + 1]);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Now showing Previous city", Toast.LENGTH_SHORT).show();

                String currentCityName = ListFrag.weathers.get(0).getCity();

                currentIndex =+ idsList.indexOf(LocationIdDictionary.getIDsDictionary(currentCityName));

                if (currentIndex == 0) {
                    currentIndex = idsList.size();
                }
                ListFrag.newInstance(ids[currentIndex - 1]);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
        tvCity.setText("Weather in : " + ListFrag.weathers.get(index).getCity());

//        if(WeatherDAO.weathers.get(index).getMake().equals("Volkswagen")){
//            ivMake.setImageResource(R.drawable.volkswagen);
//        }else if(WeatherDAO.weathers.get(index).getMake().equals("Nissa")){
//            ivMake.setImageResource(R.drawable.nissan);
//        }else {
//            ivMake.setImageResource(R.drawable.mercedes);
//        }


        //switch layouts
        if (findViewById(R.id.layout_portrait) != null) {
            nextBtn.setVisibility(View.GONE);
            prevBtn.setVisibility(View.GONE);

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.weatherDetailsFrag))
                    .hide(fragmentManager.findFragmentById(R.id.listFrag))
                    .addToBackStack(null)
                    .commit();
        }


    }


}
