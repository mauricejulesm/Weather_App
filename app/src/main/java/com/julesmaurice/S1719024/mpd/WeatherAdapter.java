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

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    ArrayList<Weather> weathers;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    public WeatherAdapter(Context context, ArrayList<Weather> weatherList) {
        weathers = weatherList;
        activity = (ItemClicked) context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivWeather;
        TextView tvCity, tvWind, tvTemp, tvHumid;

        public ViewHolder(View itemView) {
            super(itemView);
            ivWeather = itemView.findViewById(R.id.ivWeather);
            tvCity = itemView.findViewById(R.id.rowTvCity);
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

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(weathers.get(position));

        holder.tvCity.setText(weathers.get(position).getCity());
        holder.tvWind.setText(weathers.get(position).getWindSpeed());
        holder.tvTemp.setText(weathers.get(position).getMinTemp());
        holder.tvHumid.setText(weathers.get(position).getHumidity());

//        holder.ivMake.setImageResource(R.drawable.ic_launcher_foreground);

    }

    @Override
    public int getItemCount() {


        return weathers.size();
    }
}
