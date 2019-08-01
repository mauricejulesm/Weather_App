package com.julesmaurice.S1719024.mpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.julesmaurice.S1719024.mpd.models.LocationIdDictionary;

public class HomeActivity extends AppCompatActivity {

    private static String currentCityID;
    CardView cvGlasgow, cvNY, cvOman, cvLondon, cvMauritius, cvBangladesh, cvRwanda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvGlasgow = findViewById(R.id.cvGlasgow);
        cvNY = findViewById(R.id.cvNY);
        cvOman = findViewById(R.id.cvOman);
        cvLondon = findViewById(R.id.cvLondon);
        cvMauritius = findViewById(R.id.cvMauritius);
        cvBangladesh = findViewById(R.id.cvBangladesh);
        cvRwanda = findViewById(R.id.cvRwanda);


        cvGlasgow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,
                        com.julesmaurice.S1719024.mpd.MainActivity.class);

                // retrieving the location specific id from (city, id) dictionary
                currentCityID = LocationIdDictionary.getIDsDictionary(cvGlasgow.getChildAt(0).getTag().toString());
                startActivity(intent);
            }
        });
        cvNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,
                        com.julesmaurice.S1719024.mpd.MainActivity.class);

                // retrieving the location specific id from (city, id) dictionary
                currentCityID = LocationIdDictionary.getIDsDictionary(cvNY.getChildAt(0).getTag().toString());
                startActivity(intent);
            }
        });
    }


    public static String getCurrentCityID() {
        return currentCityID;
    }
}
