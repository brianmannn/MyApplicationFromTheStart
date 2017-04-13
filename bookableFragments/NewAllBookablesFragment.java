package com.crazyhands.myapplicationfromthestart.bookableFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crazyhands.myapplicationfromthestart.Adapters.SimpleListAdapter;
import com.crazyhands.myapplicationfromthestart.Items.List_item;
import com.crazyhands.myapplicationfromthestart.R;
import com.crazyhands.myapplicationfromthestart.loader.QueryUtils;

import java.util.ArrayList;
import java.util.List;

import static com.crazyhands.myapplicationfromthestart.App.AppConfig.URL_EVENTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewAllBookablesFragment extends Fragment {


    public NewAllBookablesFragment() {
        // Required empty public constructor
    }

    private SimpleListAdapter mAdapter;

    String TAG = "EventsActivity";
    TextView textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_bookables, container, false);


        textview = (TextView) rootView.findViewById(R.id.empty_view);



        final RequestQueue requestque = Volley.newRequestQueue(getActivity());

        StringRequest request = new StringRequest(Request.Method.GET, URL_EVENTS,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Events Response: " + response.toString());
                        //textview.setText(response);

                        // Extract relevant fields from the JSON response and create a list of  List_items
                        List<List_item> eventss = QueryUtils.extractFeatureFromJson(response);

                        // Find a reference to the {@link ListView} in the layout
                        ListView eventsListView = (ListView) rootView.findViewById(R.id.list);


                        // Create a new adapter that takes an empty list of events as input
                        mAdapter = new SimpleListAdapter(getActivity(), new ArrayList<List_item>());

                        // Set the adapter on the {@link ListView}
                        // so the list can be populated in the user interface
                        eventsListView.setAdapter(mAdapter);

                        if (eventss != null && !eventss.isEmpty()) {
                            mAdapter.addAll(eventss);
                            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
                            loadingIndicator.setVisibility(View.GONE);
                        }

                        requestque.stop();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textview.setText("someshit gone down!");
                        volleyError.printStackTrace();
                        Log.e(TAG, "Response error" + volleyError.getMessage());
                        Toast.makeText(getActivity().getApplicationContext(),
                                volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        String message = null;
                        if (volleyError instanceof NetworkError) {
                            message = getString(R.string.ConnectionErrorMessage);
                        } else if (volleyError instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (volleyError instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (volleyError instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }

                        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        requestque.stop();
                    }
                });
        requestque.add(request);

        return rootView;
    }


}


