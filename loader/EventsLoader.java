package com.crazyhands.myapplicationfromthestart.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.crazyhands.myapplicationfromthestart.Items.List_item;

import java.util.List;

/**
 * Created by crazyhands on 11/04/2017.
 */

public class EventsLoader extends AsyncTaskLoader<List<List_item>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EventsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EventsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EventsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<List_item> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of Events.
        List<List_item> Events = QueryUtils.fetchEarthquakeData(mUrl);
        return Events;
    }
}
