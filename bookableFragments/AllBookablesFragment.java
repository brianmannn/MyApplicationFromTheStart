package com.crazyhands.myapplicationfromthestart.bookableFragments;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crazyhands.myapplicationfromthestart.Activities.Bookables;
import com.crazyhands.myapplicationfromthestart.Adapters.SimpleListAdapter;
import com.crazyhands.myapplicationfromthestart.Items.List_item;
import com.crazyhands.myapplicationfromthestart.R;
import com.crazyhands.myapplicationfromthestart.loader.EventsLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBookablesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<List_item>>

{
    public AllBookablesFragment() {
        // Required empty public constructor
    }
    private static final String LOG_TAG = Bookables.class.getName();

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String REQUEST_URL = "http://lengthier-grooms.000webhostapp.com/get-data.php";

    /**
     * Constant value for the event loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EVENT_LOADER_ID = 1;

    /**
     * Adapter for the list of events
     */
    private SimpleListAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_bookables, container, false);


        // Find a reference to the {@link ListView} in the layout
        ListView eventsListView = (ListView) rootView.findViewById(R.id.list);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        eventsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of events as input
        mAdapter = new SimpleListAdapter(getActivity(), new ArrayList<List_item>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        eventsListView.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getActivity().getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EVENT_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
        return rootView;
    }

    @Override
    public Loader<List<List_item>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new EventsLoader(getActivity(), REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<List_item>> loader, List<List_item> events) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Events}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (events != null && !events.isEmpty()) {
            mAdapter.addAll(events);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<List_item>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}


