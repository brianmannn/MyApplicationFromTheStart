package com.crazyhands.myapplicationfromthestart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crazyhands.myapplicationfromthestart.Adapters.SimpleListAdapter;
import com.crazyhands.myapplicationfromthestart.Items.List_item;
import com.crazyhands.myapplicationfromthestart.R;
import com.crazyhands.myapplicationfromthestart.loader.QueryUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VolleyTestActivity extends Activity {

    private SimpleListAdapter mAdapter;

    String TAG = "EventsActivity";
    TextView textview1;

    String theServersURL = "http://lengthier-grooms.000webhostapp.com/get-data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        textview1 = (TextView) findViewById(R.id.textview1);



        final RequestQueue requestque = Volley.newRequestQueue(VolleyTestActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, theServersURL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Events Response: " + response.toString());
                        //textview1.setText(response);

                        // Extract relevant fields from the JSON response and create a list of  List_items
                        List<List_item> eventss = QueryUtils.extractFeatureFromJson(response);

                        // Find a reference to the {@link ListView} in the layout
                        ListView eventsListView = (ListView) findViewById(R.id.list);


                        // Create a new adapter that takes an empty list of events as input
                        mAdapter = new SimpleListAdapter(VolleyTestActivity.this, new ArrayList<List_item>());

                        // Set the adapter on the {@link ListView}
                        // so the list can be populated in the user interface
                        eventsListView.setAdapter(mAdapter);

                        if (eventss != null && !eventss.isEmpty()) {
                            mAdapter.addAll(eventss);
                        }

                        requestque.stop();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textview1.setText("someshit gone down!");
                        error.printStackTrace();
                        Log.e(TAG, "Response error" + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        requestque.stop();
                    }
                });
                requestque.add(request);
    }}