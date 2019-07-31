package com.julesmaurice.S1719024.mpd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 *
 *
 *
 * The fragment to show the details for the selected city
 * A simple {@link Fragment} subclass.
 */
public class DetailFrag extends Fragment {


    public DetailFrag() {
        // Required empty public constructor
    }


    /**
     * @param inflater the layout inflater
     * @param container the current view container
     * @param savedInstanceState the current instance
     * @return returns the fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

}
