package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by mac on 29/07/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter( Context context, int resource,  List<Earthquake> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View v =convertView;
        if(v==null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_design, parent, false);
        }

        Earthquake text=getItem(position);

        TextView mag=(TextView)v.findViewById(R.id.mag);

        mag.setText(text.getMag());

        TextView city=(TextView)v.findViewById(R.id.city);
        city.setText(text.getCity());

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(text.getDate());

        // Find the TextView with view ID date
        TextView dateView = (TextView) v.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) v.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        return v;
    }
}
