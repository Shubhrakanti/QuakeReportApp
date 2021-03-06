package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by giddu on 2/26/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        double mag = currentEarthquake.getMagnitude();
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.format(mag);


        TextView magText = (TextView) listItemView.findViewById(R.id.mag_text_view);
        magText.setText(String.valueOf(mag));

        TextView directionText = (TextView) listItemView.findViewById(R.id.location_offset);
        directionText.setText(currentEarthquake.getDirection());

        TextView locationText = (TextView) listItemView.findViewById(R.id.place_text_view);
        locationText.setText(currentEarthquake.getActualLocation());


        long timeInMilliseconds = currentEarthquake.getDate();
        Date dateObject = new Date(timeInMilliseconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormat.format(dateObject);

        TextView dateText = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateText.setText(dateToDisplay);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(timeToDisplay);

        GradientDrawable magnitudeCircle = (GradientDrawable) magText.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        return listItemView;

    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
