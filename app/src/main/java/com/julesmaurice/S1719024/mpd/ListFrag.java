package com.julesmaurice.S1719024.mpd;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julesmaurice.S1719024.mpd.dao.WeatherXmlFeedsParser;
import com.julesmaurice.S1719024.mpd.models.Weather;

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

    public static ArrayList<Weather> weathers;
    static ProgressDialog progressDialog;

    //default city id
    static String currentCityID;

    //checking refreshTime
    boolean refreshInMorning = true;


    public ListFrag() {
        // default recommended constructor
    }


    /**
     * @param id the id of the city to be displayed
     */
    public static void newInstance(String id) {
        currentCityID = id;
        new ListFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // making sure that the fragment can show menu
        setHasOptionsMenu(true);

        // inflating the fragment to the view
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    /**
     * @param savedInstanceState the current instance
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //initializing the new progress dialog
        progressDialog = new ProgressDialog(this.getContext());
        //processor
        // execute the process in the background operation
        // this process runs in the background on a different thread and it doesn't hinder other processes.
        new BackgroundTasksProcessor().execute(currentCityID);
    }

    /**
     * Class for using threads in the background
     * The inner class to process the async tasks in the background.
     */
    public class BackgroundTasksProcessor extends AsyncTask<String, Void, String> {

        /**
         * Run after the async task is done.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading feeds... make sure you're connected to internet");
            progressDialog.show();


        }

        /**
         * This method runs on a different thread in the background to make the usability smooth and don't hinder the display of the views.
         *
         * @param strings takes the what you want to pass to the method that parses the data.
         * @return this exception if there is a error while parsing data.
         */
        @Override
        protected String doInBackground(String... strings) {

            weathers = WeatherXmlFeedsParser.parseWeatherXML(strings[0]);

            return String.valueOf(weathers);
        }

        /**
         * This method is called when the do in background is finished and
         * it is the safest place to arsing returned values/feeds to the views.
         *
         * @param s takes what was returned from the do in background method
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Weather in " + weathers.get(0).getCity());
            }

            // assigning the returned data feeds to the recycler view of the weather.
            recyclerView = view.findViewById(R.id.list);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ListFrag.this.getActivity());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new WeatherAdapter(ListFrag.this.getActivity(), R.layout.row_layout, weathers);
            recyclerView.setAdapter(adapter);

        }
    }

    public void refreshWeatherInfo(){
        BackgroundTasksProcessor bp = new BackgroundTasksProcessor();
        bp.execute(currentCityID);    }
}
