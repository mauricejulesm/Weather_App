package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.julesmaurice.S1719024.mpd.models.LocationIdDictionary;

public class HomeActivity extends AppCompatActivity {
    private static String currentCityID;
    //checking refreshTime
    boolean refreshInMorning = true;

    // declaring views
    CardView cvGlasgow, cvNY, cvOman, cvLondon, cvMauritius, cvBangladesh, cvRwanda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // assigning views
        cvGlasgow = findViewById(R.id.cvGlasgow);
        cvNY = findViewById(R.id.cvNY);
        cvOman = findViewById(R.id.cvOman);
        cvLondon = findViewById(R.id.cvLondon);
        cvMauritius = findViewById(R.id.cvMauritius);
        cvBangladesh = findViewById(R.id.cvBangladesh);
        cvRwanda = findViewById(R.id.cvRwanda);


        // when glasgow is selected
        cvGlasgow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvGlasgow.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvNY.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvOman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvOman.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvLondon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvLondon.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvBangladesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvBangladesh.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvMauritius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvMauritius.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });
        cvRwanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                // retrieving the location specific id from (city, id) dictionary
                ListFrag.newInstance(LocationIdDictionary.getIDsDictionary(cvRwanda.getChildAt(0).getTag().toString()));
                startActivity(intent);
            }
        });

    }

    /**
     * @param menu the menu layout
     * @return options of the menu to the activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem nightRefresh = menu.findItem(R.id.refresh8pm);

        if (refreshInMorning = false){
            nightRefresh.setChecked(true);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                item.setChecked(true);
                Toast.makeText(HomeActivity.this, "Refreshed the RSS Feeds", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh8am:
                item.setChecked(true);
                Toast.makeText(HomeActivity.this, "The RSS Feeds was set to refresh at 8 am", Toast.LENGTH_LONG).show();
                break;
            case R.id.refresh8pm:
                item.setChecked(true);
                refreshInMorning = false;
                Toast.makeText(HomeActivity.this, "The RSS Feeds was set to refresh at 8 pm", Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
