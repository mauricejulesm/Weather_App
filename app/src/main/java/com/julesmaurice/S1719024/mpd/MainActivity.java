package com.julesmaurice.S1719024.mpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.julesmaurice.S1719024.mpd.dao.WeatherDAO;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ItemClicked{

    TextView tvDTemp, tvDDesc, tvCity;

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.Fragment listFrag;
    android.support.v4.app.Fragment ownerInfoFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvDTemp = findViewById(R.id.tvDetailTemp);
        tvDDesc = findViewById(R.id.tvDetailDesc);
        tvCity = findViewById(R.id.tvDetailCity);

        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.listFrag);
        ownerInfoFrag = fragmentManager.findFragmentById(R.id.ownerInfoFrag);

        fragmentManager.beginTransaction()
                .show(listFrag)
                .show(ownerInfoFrag)
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
        onItemClicked(0);

    }

    @Override
    public void onItemClicked(int index) {
        tvDTemp.setText(WeatherDAO.weathers.get(index).getDegree_celsius());
        tvDDesc.setText(WeatherDAO.weathers.get(index).getDescription());
        tvCity.setText(WeatherDAO.weathers.get(index).getCity());

//        if(WeatherDAO.weathers.get(index).getMake().equals("Volkswagen")){
//            ivMake.setImageResource(R.drawable.volkswagen);
//        }else if(WeatherDAO.weathers.get(index).getMake().equals("Nissa")){
//            ivMake.setImageResource(R.drawable.nissan);
//        }else {
//            ivMake.setImageResource(R.drawable.mercedes);
//        }
    }
   }
