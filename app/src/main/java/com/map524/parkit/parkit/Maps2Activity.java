package com.map524.parkit.parkit;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //get the intent, and thereby the lat and lng from the intent of the choice of the location
        //need the intent that calls the search activity to pass the lat and lng as string extras
        //these will need to be changed when search activity is wired correctly
        Double pickedLat=0.0, pickedLng=0.0;
        //call a new Geocoder object
        Geocoder x = new Geocoder(this);

        //Using List<Address of PLots, try to getFromLocation(double lat, double lng, int max results);
        List<Address> addressList = null;

        try{
            //get from location using lat, lng and max results: returns a list of addresses
            addressList = x.getFromLocation(pickedLat, pickedLng, 1);

        }catch(Exception e){}

        Address a = addressList.get(0);
        float zoom = 14.0f;
        LatLng latLng = new LatLng(a.getLatitude(), a.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

}
