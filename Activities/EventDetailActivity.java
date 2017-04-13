package com.crazyhands.myapplicationfromthestart.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import static com.crazyhands.myapplicationfromthestart.App.AppConfig.URL_EVENTS_WHERE;

public class EventDetailActivity extends AppCompatActivity {
    String TAG = "EventDetail";
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Bundle extras = getIntent().getExtras();
        String id = Integer.toString(extras.getInt("id"));

        //String id = toString().ids;

        textview = (TextView)findViewById(R.id.EventDetailtextview);

        final RequestQueue requestque = Volley.newRequestQueue(EventDetailActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, URL_EVENTS_WHERE + id,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Events Response: " + response.toString());
                        textview.setText(response);




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textview.setText("someshit gone down!");
                        volleyError.printStackTrace();
                        Log.e(TAG, "Response error" + volleyError.getMessage());
                        Toast.makeText(getApplicationContext(),
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

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        requestque.stop();
                    }
                });
        requestque.add(request);


    }
}
