package com.julesmaurice.S1719024.mpd;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
  Student Name: Jules Maurice Mulisa
  Student ID: S1719024
  Email: JMULIS200@caledonian.ac.uk
 *


 *
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {

RecyclerView recyclerView;
RecyclerView.Adapter adapter;
RecyclerView.LayoutManager layoutManager;
View view;

    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);
    return  view;
    }

    /**
     *
     * @param savedInstanceState the current instance
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WeatherAdapter(this.getActivity(), R.layout.row_layout, MainActivity.weathers);
        recyclerView.setAdapter(adapter);
    }


}
