package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends ArrayAdapter {
    private static final String LOCATION_SEPARATOR = " of ";

    public CustomAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String primaryLocation;
        String locationOffset;


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = (Earthquake) getItem(position);

        TextView magnitudeText = listItemView.findViewById(R.id.magnitude);
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        String magnitude = magnitudeFormatter.format(currentEarthquake.getMagnitude());
        magnitudeText.setText(magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquake.getCity();
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            primaryLocation = originalLocation.split(LOCATION_SEPARATOR)[1];
            locationOffset = originalLocation.split(LOCATION_SEPARATOR)[0] + LOCATION_SEPARATOR;
        } else {
            primaryLocation = originalLocation;
            locationOffset = "Near the";
        }

        TextView primaryLocationText = listItemView.findViewById(R.id.primary_location);
        primaryLocationText.setText(primaryLocation);

        TextView secondaryLocationText = listItemView.findViewById(R.id.location_offset);
        secondaryLocationText.setText(locationOffset);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateText = listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateText.setText(formattedDate);

        TextView timeText = listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeText.setText(formattedTime);


        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
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


    private String formatTime(Date dateObject) {
        //Return the formatted date string (i.e. "4:30 PM") from a Date object.
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
