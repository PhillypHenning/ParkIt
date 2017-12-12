package com.map524.parkit.parkit;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;

import org.json.JSONArray;
import org.json.JSONObject;
import com.map524.parkit.parkit.ParkingDataModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SearchActivity extends AppCompatActivity {
    Button mButton;
    EditText dEdit;
    Switch cSwitch;
    boolean cSwitchChecked = false;
    boolean sdSwitchChecked = false;
    Switch sdSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Flag cost - sort in descending cost (Free++)
        // Flag dist = sort in descending distance

        mButton = (Button)findViewById(R.id.search_search);
        dEdit = (EditText) findViewById(R.id.search_name);
        cSwitch = (Switch) findViewById(R.id.search_low_cost);
        sdSwitch = (Switch) findViewById(R.id.search_short_dist);

        cSwitch.setChecked(false);
        sdSwitch.setChecked(false);

        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cSwitchChecked = true;
                    Log.v("cSwitch", "ON");
                }else{
                    cSwitchChecked = false;
                    Log.v("cSwitch", "OFF");
                }
            }
        });

        sdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    sdSwitchChecked = true;
                    Log.v("cSwitch", "ON");
                }else{
                    sdSwitchChecked = false;
                    Log.v("cSwitch", "OFF");
                }
            }
        });

        mButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        // Using the supplied user values
                        // Find closest lot to destination
                        Address Dest = findAddress(dEdit.getText().toString());
                        getClosestLot(Dest);
                        //Apply ordering
                        if(!sdSwitchChecked && !cSwitchChecked){
                            ParkingDataModel.shakeData(false, false);
                        }else if(!sdSwitchChecked && cSwitchChecked){
                            ParkingDataModel.shakeData(false, true);
                        }else if(sdSwitchChecked && !cSwitchChecked){
                            ParkingDataModel.shakeData(true, false);
                        }else{
                            ParkingDataModel.shakeData(true, true);
                        }
                        // Begin NearbyList Activity
                    }
                }
        );

    }
    public Address findAddress(String wantToGo){
        Geocoder gc = new Geocoder(getApplicationContext());
        List<Address> addresses = null;
        try{
            addresses = gc.getFromLocationName(wantToGo, 5);
        }catch(IOException ioe){
            Log.d("CAN'T FIND LOCATION", ioe.getMessage());
        }
        return addresses.get(0);

    }
    public void getClosestLot(Address dest){
        ParkingDataModel closestLot = MainActivity.data.get(0);
        for(int i = 0; i < MainActivity.data.size(); i++) {
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + MainActivity.data.get(i).getLat().toString() + "," + MainActivity.data.get(i).getLng().toString() + "&destinations=" + dest.getLatitude() + "," + dest.getLongitude() + "&units=metric&key=AIzaSyCaWJOZSIN6wEaCnelo-SrlYgNz-6NN3oQ");
                // INSTEAD OF A SINGLE DEST POINT INSTEAD SEND A LIST
            } catch (MalformedURLException e) {
                Log.d("Malformed URL exception: ", e.getMessage());
            }
        }
    }
}
