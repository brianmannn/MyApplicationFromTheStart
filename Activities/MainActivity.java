package com.crazyhands.myapplicationfromthestart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crazyhands.myapplicationfromthestart.R;
import com.crazyhands.myapplicationfromthestart.bookableFragments.NewAllBookablesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tobookables = (Button) findViewById(R.id.bookablesbutton);
        tobookables.setOnClickListener(new View.OnClickListener() {
            //The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent toMyClassedIntent = new Intent(MainActivity.this, Bookables.class);
                startActivity(toMyClassedIntent);
            }
        });
        Button totest = (Button) findViewById(R.id.testButton);
        totest.setOnClickListener(new View.OnClickListener() {
            //The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //add intents
            }
        });

        Button tonewfragment = (Button) findViewById(R.id.ManageClassesButton);
        tonewfragment.setOnClickListener(new View.OnClickListener() {
            //The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent toMytest = new Intent(MainActivity.this, NewAllBookablesFragment.class);
                startActivity(toMytest);
            }
        });
    }
}
