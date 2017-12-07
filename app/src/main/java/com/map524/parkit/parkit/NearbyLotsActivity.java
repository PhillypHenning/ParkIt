package com.map524.parkit.parkit;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class NearbyLotsActivity extends Activity {
    private ListView lv = null;
    private Context mcontext = null;
    private Boolean canGetLocation = false;
    LocationManager locationManager = null;
    LocationListener locationListener = null;
    Location location = null;


    // STATIC MEMBERS
    public static float latitude;
    public static float longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_lots);
        mcontext = getApplicationContext();


        this.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        this.locationListener = new LocationListener() {
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

        // Check for permissions
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12345);
        }else{
            Log.d("Error getting permission","PERMISSION DENIED");
        }
        this.location = getLocation();

        //Create the listview
        lv = (ListView) findViewById(R.id.nearby_lv);

        // Create Adapter
        lv.setAdapter(new NearbyLotAdapter(this, location));
    }

    public Location getLocation(){
        Location location = null;
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
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long)0, (float)0, this.locationListener);
                Log.d("Network enabled","");
                if(locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(location != null){
                        latitude = (float)location.getLatitude();
                        longitude = (float)location.getLongitude();
                    }
                }
            }
            if(isGPSEnabled){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long)0, (float)0, this.locationListener);
                Log.d("GPS ENABLED", "");
                if(locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null){
                        latitude = (float)location.getLatitude();
                        longitude = (float)location.getLongitude();
                    }
                }
            }
        }catch( SecurityException e){
            Log.d("EXCPETION WHILE TRYING TO GET LOCATION","UNABLE TO GET LOCATION SERVICE");
        }
        return location;
    }
}
