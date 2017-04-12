package com.crazyhands.myapplicationfromthestart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crazyhands.myapplicationfromthestart.R;

import org.json.JSONException;

public class VolleyTestActivity extends Activity {

    String TAG = "EventsActivity";
    TextView textview1;
    TextView textview2;
    TextView textview3;
    Button button;
    String theServersURL = "http://lengthier-grooms.000webhostapp.com/get-data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        button = (Button)findViewById(R.id.buttonidview);
        textview1 = (TextView)findViewById(R.id.textview1);
        textview2 = (TextView)findViewById(R.id.textview2);
        textview3 = (TextView)findViewById(R.id.textview3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final RequestQueue requestque = Volley.newRequestQueue(VolleyTestActivity.this);

                StringRequest request = new StringRequest(Request.Method.GET, theServersURL,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Events Response: " + response.toString());
                                    textview1.setText(response);
                                    requestque.stop();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textview1.setText("someshit gone down!");
                                error.printStackTrace();
                                Log.e(TAG, "Login Error: " + error.getMessage());
                                Toast.makeText(getApplicationContext(),
                                        error.getMessage(), Toast.LENGTH_LONG).show();
                                requestque.stop();
                            }
                        });
                requestque.add(request);
            }
        });


    }
}
