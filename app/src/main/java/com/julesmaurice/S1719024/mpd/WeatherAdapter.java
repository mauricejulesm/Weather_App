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
 * <p>
 * <p>
 * The adapter class to handle to the weather listing recycler view
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    ArrayList<Weather> weathers;
    private final int layoutResource;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    /**
     * @param context     the current context
     * @param resource    the resource id
     * @param weatherList the list of weathers available.
     */
    public WeatherAdapter(Context context, int resource, ArrayList<Weather> weatherList) {
        this.weathers = weatherList;
        this.layoutResource = resource;
        this.activity = (ItemClicked) context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivWeather;
        TextView tvDay, tvWindSpeed, tvMinTemp, tvHumid, tvPubDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ivWeather = itemView.findViewById(R.id.ivWeather);
            tvDay = itemView.findViewById(R.id.rowDay);
            tvPubDate = itemView.findViewById(R.id.tvDateOfWeek);
            tvWindSpeed = itemView.findViewById(R.id.tvWindSpeed);
            tvMinTemp = itemView.findViewById(R.id.tvMinTemp);
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
     * @param viewType  the type the your view
     * @return returns the a new view holder object
     */
    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * @param holder   the view holder
     * @param position the position of the just selected view or card on the view
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(weathers.get(position));

        String today = weathers.get(position).getDayOfTheWeek();

        holder.tvDay.setText(today);
        holder.tvPubDate.setText(weathers.get(position).getDateOfForecast());
        holder.tvWindSpeed.setText(weathers.get(position).getWindSpeed());
        holder.tvMinTemp.setText(weathers.get(position).getMinTemp());
        holder.tvHumid.setText(weathers.get(position).getHumidity());

        // setting weather icon depending on the day's weather

        if (today.contains("Partly Cloudy")) {
            holder.ivWeather.setImageResource(R.drawable.partly_cloudy);
        } else if (today.contains("Sunny Intervals")) {
            holder.ivWeather.setImageResource(R.drawable.sunny_intervals);
        } else if (today.contains("Thundery Showers")) {
            holder.ivWeather.setImageResource(R.drawable.thundery_showers);
        } else if (today.contains("Light Rain")) {
            holder.ivWeather.setImageResource(R.drawable.light_rain);
        } else if (today.contains("Sunny")) {
            holder.ivWeather.setImageResource(R.drawable.sunny);
        } else if (today.contains("Light Cloud")) {
            holder.ivWeather.setImageResource(R.drawable.light_cloud);
        } else if (today.contains("Mist")) {
            holder.ivWeather.setImageResource(R.drawable.mist_w);
        } else if (today.contains("Clear Sky")) {
            holder.ivWeather.setImageResource(R.drawable.clear_sky);
        } else {
            holder.ivWeather.setImageResource(R.drawable.default_icon);
        }

    }

    /**
     * @return returns the size of the items on the list of recycler view
     */
    @Override
    public int getItemCount() {


        return weathers.size();
    }
}
