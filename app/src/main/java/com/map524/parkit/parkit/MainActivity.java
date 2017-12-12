package com.map524.parkit.parkit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.Manifest;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_INTERNET = 2;
    public static Boolean canGetLocation = false;
    public static LocationManager locationManager = null;
    public static LocationListener locationListener = null;
    public static Location location = null;
    public static ArrayList<ParkingDataModel> data = null;
    public static Integer LAST_POSITION;

    private Context mcontext = null;


    public final static String KEY = "key=AIzaSyDz8nQKYgugVdWcOio6fkN5blU78j5IM_A";


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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        MainActivity.data = new ArrayList<ParkingDataModel>();

        mcontext = getApplicationContext();
        MainActivity.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        MainActivity.locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //-- LOCATION --\\
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_ACCESS_INTERNET);
        }

        MainActivity.location = getLocation();
        ParkingDataModel pdm = new ParkingDataModel();
        pdm.getParkingDataModelData(mcontext);
        ArrayList<ParkingDataModel> test = MainActivity.data;

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case MainActivity.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permission was granted
                    Log.d("Locations permission granted:","PackageManager.PERMISSION_GRANTED");
                }else{
                    // permission was not
                    Log.d("Location permission was not granted:","PackageManager.PERMISSION_GRANTED");
                }
            }case MainActivity.MY_PERMISSIONS_REQUEST_ACCESS_INTERNET:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Internet permission granted:","PackageManager.PERMISSION_GRANTED");
                }else{
                    Log.d("Internet permission was not granted:","PackageManager.PERMISSION_GRANTED");

                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    public Location getLocation(){
        Location Nlocation = null;
        Location Glocation = null;
        try{
            locationManager = (LocationManager) mcontext.getSystemService(LOCATION_SERVICE);

            //getting GPS status
            Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && !isNetworkEnabled){
                // no provider available
                Log.d("No provider available: ", "QUITTING PROGRAM");
                System.exit(1);
            }

            this.canGetLocation = true;

            if (isNetworkEnabled){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long)0, (float)0, MainActivity.locationListener);
                Log.d("Network enabled","");
                if(locationManager != null){
                    Nlocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
            if(isGPSEnabled){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long)0, (float)0, this.locationListener);
                Log.d("GPS ENABLED", "");
                if(locationManager != null){
                    Glocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            }
        }catch( SecurityException e){
            Log.d("EXCPETION WHILE TRYING TO GET LOCATION","UNABLE TO GET LOCATION SERVICE");
        }

        return Glocation != null ? Glocation : Nlocation;
    }
}



