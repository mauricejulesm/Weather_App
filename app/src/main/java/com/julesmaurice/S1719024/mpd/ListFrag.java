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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.julesmaurice.S1719024.mpd.models.Weather;
import com.julesmaurice.S1719024.mpd.dao.XmlWeatherFeedsParser;

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

    static Weather weatherObj;
    public static ArrayList<Weather> weathers;
    static ProgressDialog progressDialog;

    //default city id
    static String currentCityID;


    public ListFrag() {
        // default recommended constructor
    }


    public static ListFrag newInstance(String id) {
        currentCityID = id;
        return new ListFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //retrieving the currency city's location id

        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    /**
     * @param savedInstanceState the current instance
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //show a progress dialog
        progressDialog = new ProgressDialog(this.getContext());

        // execute the process in the background operation
        new ProcessInBackground().execute(currentCityID);


//        weathers = new ArrayList<>();



    }


    /**
     * Class for using threads in the background
     * The inner class to process the async tasks in the background.
     */
    public class ProcessInBackground extends AsyncTask<String, Void, String> {

        /**
         * Run after the async task is done.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading weather rss feeds... make sure you are connected to internet");
            progressDialog.show();
        }

        /**
         * @param strings takes the what you want to pass to the method that parses the data.
         * @return this exception if there is a error while parsing data.
         */
        @Override
        protected String doInBackground(String... strings) {

            weathers = XmlWeatherFeedsParser.parseXML(strings[0]);

            return String.valueOf(weathers);
        }

        /**
         * @param s takes what was returned from the do in background method
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setTitle("Weather in "+weathers.get(0).getCity());


            recyclerView = view.findViewById(R.id.list);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ListFrag.this.getActivity());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new WeatherAdapter(ListFrag.this.getActivity(), R.layout.row_layout, weathers);
            recyclerView.setAdapter(adapter);


            progressDialog.dismiss();

//            onItemClicked(0);

        }
    }


    /**
     * @param menu the menu layout
     * @return options of the menu to the activity
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(this.getContext(), "Refreshed the RSS Feeds", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh8am:
                Toast.makeText(this.getContext(), "The RSS Feeds was set to refresh at 8 am", Toast.LENGTH_LONG).show();
                break;
            case R.id.refresh8pm:
                Toast.makeText(this.getContext(), "The RSS Feeds was set to refresh at 8 pm", Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
