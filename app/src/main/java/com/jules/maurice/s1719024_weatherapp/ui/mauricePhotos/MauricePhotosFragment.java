package com.jules.maurice.s1719024_weatherapp.ui.mauricePhotos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jules.maurice.s1719024_weatherapp.R;

public class MauricePhotosFragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;

    public static MauricePhotosFragment newInstance(){
        MauricePhotosFragment fragment = new MauricePhotosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maurice_photos, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        refreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // things to happen on refresh
            }
        });
    }
}
