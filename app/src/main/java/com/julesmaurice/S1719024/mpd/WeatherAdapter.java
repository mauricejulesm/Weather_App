package com.julesmaurice.S1719024.mpd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.julesmaurice.S1719024.mpd.models.Weather;

import java.util.ArrayList;

/**
 * Student Name: Jules Maurice Mulisa
 * Student ID: S1719024
 * Email: JMULIS200@caledonian.ac.uk
 *
 *
 * The adapter class to handle to the weather listing recycler view
 *
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    ArrayList<Weather> weathers;
    private final int layoutResource;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    /**
     * @param context the current context
     * @param resource the resource id
     * @param weatherList the list of weathers available.
     */
    public WeatherAdapter(Context context, int resource, ArrayList<Weather> weatherList) {
        this.weathers = weatherList;
        this.layoutResource = resource;
        this.activity = (ItemClicked) context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivWeather;
        TextView tvDay, tvWind, tvTemp, tvHumid;

        public ViewHolder(View itemView) {
            super(itemView);
            ivWeather = itemView.findViewById(R.id.ivWeather);
            tvDay = itemView.findViewById(R.id.rowDay);
            tvWind = itemView.findViewById(R.id.tvWindSpeed);
            tvTemp = itemView.findViewById(R.id.tvTemp);
            tvHumid = itemView.findViewById(R.id.tvHumid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(weathers.indexOf((Weather) v.getTag()));
                }
            });
        }
    }

    /**
     * @param viewGroup the current view
     * @param viewType the type the your view
     * @return returns the a new view holder object
     */
    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * @param holder the view holder
     * @param position the position of the just selected view or card on the view
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(weathers.get(position));

        holder.tvDay.setText(weathers.get(position).getDatyOfTheWeek());
        holder.tvWind.setText(weathers.get(position).getWindSpeed());
        holder.tvTemp.setText(weathers.get(position).getMinTemp());
        holder.tvHumid.setText(weathers.get(position).getHumidity());

//        holder.ivMake.setImageResource(R.drawable.ic_launcher_foreground);

    }

    /**
     * @return returns the size of the items on the list of recycler view
     */
    @Override
    public int getItemCount() {


        return weathers.size();
    }
}
