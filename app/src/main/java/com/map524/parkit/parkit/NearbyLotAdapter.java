package com.map524.parkit.parkit;

import android.*;
import android.Manifest;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by pahenning-folz on 11/14/2017.
 */

public class NearbyLotAdapter extends BaseAdapter {
    private Context context;

    private static LayoutInflater mInflater;

    public NearbyLotAdapter(Context context){
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    @Override
    public int getCount(){
        return MainActivity.data.size();
    }

    @Override
    public Bundle getItem(int position){
        Bundle bundle = new Bundle();
        bundle.putString("Address", MainActivity.data.get(position).getAdr());
        bundle.putString("RateHH", MainActivity.data.get(position).getRhh());
        bundle.putString("CarParkType", MainActivity.data.get(position).getCpt());
        bundle.putString("MaxHeight", MainActivity.data.get(position).getMaxhet());
        bundle.putString("Title", MainActivity.data.get(position).getTitle());
        bundle.putString("TTC", MainActivity.data.get(position).getTtc().toString());
        bundle.putString("Payment Methods", MainActivity.data.get(position).getPm());
        bundle.putString("Distance", MainActivity.data.get(position).getDistance());
        bundle.putString("Eta", MainActivity.data.get(position).getEta());
        bundle.putString("Rates", MainActivity.data.get(position).getRates());
        bundle.putStringArrayList("Notes", MainActivity.data.get(position).getNotes());
        bundle.putStringArrayList("POS", MainActivity.data.get(position).getPos());

        return bundle;
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        if(vi ==null)
            vi = mInflater.inflate(R.layout.nearby_list_item, null);

        // Getting TextView items
        TextView adr = (TextView) vi.findViewById(R.id.nearby_list_item_addr);
        TextView city = (TextView) vi.findViewById(R.id.nearby_list_item_city);
        TextView eta = (TextView) vi.findViewById(R.id.nearby_list_item_timest);
        TextView dist = (TextView) vi.findViewById(R.id.nearby_list_item_dist);
        TextView rate = (TextView) vi.findViewById(R.id.nearby_list_item_rate);
        TextView pymt = (TextView) vi.findViewById(R.id.nearby_list_item_payment);
        ImageButton map = (ImageButton) vi.findViewById(R.id.nearby_list_item_image);

        try {
            // Setting values
            adr.setText(MainActivity.data.get(position).getAdr());

            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(MainActivity.data.get(position).getLat(), MainActivity.data.get(position).getLng(), 1);
            if(addresses.size() > 0){
                city.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getPostalCode());
            }
            else{
                Log.d("Unable to find city: ","Lng: " + MainActivity.data.get(position).getLng() + " | Lat: " + MainActivity.data.get(0).getLat());
            }

            //destination calculation
            dist.setText(MainActivity.data.get(position).getDistance());
            eta.setText(MainActivity.data.get(position).getEta());


            //-Set Button Click-\\

            map.setOnClickListener(new CustomOnClickListener(MainActivity.data.get(position).getLat(), MainActivity.data.get(position).getLng(), (float)MainActivity.location.getLatitude(), (float)MainActivity.location.getLongitude(), this.context));

            rate.setText(MainActivity.data.get(position).getRhh());
            pymt.setText(MainActivity.data.get(position).getPm());

        }catch(IOException e){
            Log.d("IOException while getting view: ", e.getMessage());
        }
        return vi;
    }
}


