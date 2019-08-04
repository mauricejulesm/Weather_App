package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.julesmaurice.S1719024.mpd.models.LocationIdDictionary;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked {

    ImageView ivWeather;
    // views on the layout
    TextView tvDDesc, tvDayOfWeek, tvWindDir, tvWindSpeed, tvPress,
            tvMinTemp, tvHumid, tvMaxTemp, tvSunRise, tvSunset, tvVisibility;
    FloatingActionButton nextBtn, prevBtn;

    ArrayList<String> idsList = new ArrayList<>();
    String[] ids = {"2648579", "287286", "2643743", "5128581", "934154", "1185241", "202061"};
    int currentIndex = 0;

    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idsList.addAll(Arrays.asList(ids));

        // setting up the views
        ivWeather = findViewById(R.id.ivWeather);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvDayOfWeek = findViewById(R.id.tvDayOfWeek);
        tvWindDir = findViewById(R.id.tvWindDirection);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvPress = findViewById(R.id.tvPressure);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
        tvSunRise = findViewById(R.id.tvSunRise);
        tvSunset = findViewById(R.id.tvSunSet);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvHumid = findViewById(R.id.tvHumid);
        tvVisibility = findViewById(R.id.tvVisib);


        nextBtn = findViewById(R.id.NextButton);
        prevBtn = findViewById(R.id.PrevButton);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = +idsList.indexOf(LocationIdDictionary.getIDsDictionary(ListFrag.weathers.get(0).getCity()));

                // checking if we have reached the end of the array
                if (currentIndex + 1 == idsList.size()) {
                    currentIndex = -1;
                }
                //activity with the new city's info
                ListFrag.newInstance(ids[currentIndex + 1]);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Log.i("Next", "Going to the next city");
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = +idsList.indexOf(LocationIdDictionary.getIDsDictionary(ListFrag.weathers.get(0).getCity()));
                if (currentIndex == 0) {
                    currentIndex = idsList.size();
                }
                //activity with the new city's info
                ListFrag.newInstance(ids[currentIndex - 1]);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Log.i("Prev", "Going to the prev city");
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
        if (ListFrag.weathers != null && ListFrag.weathers.size() != 0) {
            ListFrag.progressDialog.dismiss();
        }

    }

    // make sure that the floating buttons don't get removed on backstack
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!nextBtn.isShown()) {
            nextBtn.show();
            prevBtn.show();
        }
    }

    /**
     * the method to set the data to the views of the detail view
     *
     * @param index the position at which the selected card is is
     */
    @Override
    public void onItemClicked(int index) {
        String today = ListFrag.weathers.get(index).getDayOfTheWeek();

        tvDDesc.setVisibility(View.GONE);
        tvDayOfWeek.setText(today);
        tvWindDir.setText(ListFrag.weathers.get(index).getWindDirection());
        tvWindSpeed.setText(ListFrag.weathers.get(index).getWindSpeed());
        tvPress.setText(ListFrag.weathers.get(index).getPressure());
        tvHumid.setText(ListFrag.weathers.get(index).getHumidity());
        tvMinTemp.setText(ListFrag.weathers.get(index).getMinTemp());
        tvMaxTemp.setText(ListFrag.weathers.get(index).getMaxTemp());
        tvSunset.setText(ListFrag.weathers.get(index).getSunSetting());
        tvSunRise.setText(ListFrag.weathers.get(index).getSunRising());
        tvVisibility.setText(ListFrag.weathers.get(index).getVisitility());


        if (today.contains("Partly Cloudy")) {
            ivWeather.setImageResource(R.drawable.partly_cloudy);
        } else if (today.contains("Sunny Intervals")) {
            ivWeather.setImageResource(R.drawable.sunny_intervals);
        } else if (today.contains("Thundery Showers")) {
            ivWeather.setImageResource(R.drawable.thundery_showers);
        } else if (today.contains("Light Rain")) {
            ivWeather.setImageResource(R.drawable.light_rain);
        } else if (today.contains("Sunny")) {
            ivWeather.setImageResource(R.drawable.sunny);
        } else if (today.contains("Light Cloud")) {
            ivWeather.setImageResource(R.drawable.light_cloud);
        } else if (today.contains("Mist")) {
            ivWeather.setImageResource(R.drawable.mist_w);
        } else if (today.contains("Clear Sky")) {
            ivWeather.setImageResource(R.drawable.clear_sky);
        } else {
            ivWeather.setImageResource(R.drawable.default_icon);
        }





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
