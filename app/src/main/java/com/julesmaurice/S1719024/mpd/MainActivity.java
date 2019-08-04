package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.julesmaurice.S1719024.mpd.models.LocationIdDictionary;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 */

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked {

    //checking refreshTime
    boolean refreshInMorning = true;

    // views on the layout
    ImageView ivWeather;
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
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvSunRise = findViewById(R.id.tvSunRise);
        tvSunset = findViewById(R.id.tvSunSet);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
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
                Intent intent = getIntent();
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
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
                Intent intent = getIntent();
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
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
            findViewById(R.id.tvDetailMatric).setVisibility(View.GONE);
            findViewById(R.id.cvDetails).setVisibility(View.GONE);
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
        String maxT = ListFrag.weathers.get(index).getMaxTemp();
        String sunrise = ListFrag.weathers.get(index).getSunRising();

        tvDDesc.setVisibility(View.GONE);
        tvDayOfWeek.setText(today);
        tvWindDir.setText(ListFrag.weathers.get(index).getWindDirection());
        tvWindSpeed.setText(ListFrag.weathers.get(index).getWindSpeed());
        tvPress.setText(ListFrag.weathers.get(index).getPressure());
        tvHumid.setText(ListFrag.weathers.get(index).getHumidity());

        if (maxT.equalsIgnoreCase("")) {
            tvMinTemp.setText("Max Temperature: _ _ _ ");
            tvMaxTemp.setText(ListFrag.weathers.get(index).getMinTemp());
        } else {
            tvMinTemp.setText("Min Temperature:"+ListFrag.weathers.get(index).getMinTemp());
        }
        if (sunrise.equalsIgnoreCase("")) {
            tvSunRise.setText("Sunrise: _ _ _");
        } else {
            tvSunRise.setText(ListFrag.weathers.get(index).getSunRising());
        }
        tvSunset.setText(ListFrag.weathers.get(index).getSunSetting());
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
        } else if (today.contains("Drizzle")) {
            ivWeather.setImageResource(R.drawable.drizzle);
        } else {
            ivWeather.setImageResource(R.drawable.default_icon);
        }


        //switch layouts
        if (findViewById(R.id.layout_portrait) != null) {
            nextBtn.setVisibility(View.GONE);
            prevBtn.setVisibility(View.GONE);

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(fragmentManager.findFragmentById(R.id.listFrag))
                    .show(fragmentManager.findFragmentById(R.id.weatherDetailsFrag))
                    .addToBackStack(null)
                    .commit();
        }


    }

    /**
     * @param menu the menu layout
     * @return options of the menu to the activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem nightRefresh = menu.findItem(R.id.refresh8pm);

        if (refreshInMorning = false) {
            nightRefresh.setChecked(true);
        }

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                refreshWeatherNow();
                item.setChecked(true);
                Toast.makeText(this, "Refreshed the RSS Feeds", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh8am:
                item.setChecked(true);
                Toast.makeText(this, "The RSS Feeds was set to refresh at 8 am", Toast.LENGTH_LONG).show();
                break;
            case R.id.refresh8pm:
                item.setChecked(true);
                refreshInMorning = false;
                Toast.makeText(this, "The RSS Feeds was set to refresh at 8 pm", Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Refreshes the weather of the current city
     */
    public void refreshWeatherNow() {
        ListFrag.newInstance(ListFrag.currentCityID);
        ListFrag fragment = (ListFrag) getSupportFragmentManager().findFragmentById(R.id.listFrag);
        fragment.refreshWeatherInfo();
    }

}
