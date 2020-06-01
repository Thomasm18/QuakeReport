package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        Log.i(LOG_TAG, "TEST EarthquakeLoader: ");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST onStartLoading: ");
        forceLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "TEST loadInBackground: ");
        if (mUrl == null){
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
