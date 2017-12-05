package com.map524.parkit.parkit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    //-- LOCATION --\\
    FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



    // &^& Button click handler functions
    public void handle_map_click(){
        // &^& Create an intent for the click
        Intent switch_to_map_view = new Intent(MainActivity.this, NearbyLotsActivity.class);

        startActivity(switch_to_map_view);
    }

    public void handle_search_click(){
        Intent switch_to_search_view = new Intent(MainActivity.this, SearchActivity.class);

        startActivity(switch_to_search_view);
    }

    public void handle_b3_click(){
        Toast.makeText(this, "Under construction! Try again later", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //-- LOCATION --\\
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    LocationServices.MY_PERMISSION_ACCESS_FINE_LOCATION );
        }
        // NEW PLAN
        // https://stackoverflow.com/questions/5783611/android-best-way-to-implement-locationlistener-across-multiple-activities
        // Because multiple activites need the location data, I will instead create a service to handle the location


        // &^& Add button handlers by defining a reference to the R object
        ImageButton map = (ImageButton) findViewById(R.id.map_button);
        ImageButton search = (ImageButton) findViewById(R.id.search_button);
        ImageButton b3 = (ImageButton) findViewById(R.id.b3_button);

        // &^& Defining an onclick method for buttons
        map.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_map_click();
                }
            }
        );

        search.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_search_click();
                }
            }
        );

        b3.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_b3_click();
                }
            }
        );
    }
}



