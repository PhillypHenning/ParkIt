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
    public static Integer LAST_POSITION;
    private Context context;
    private static ArrayList<ParkingDataModel> data;
    private Location location = null;

    private static LayoutInflater mInflater;

    public NearbyLotAdapter(Context context, Location location){
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.location = location;
        if(this.data == null) {
            this.data = new ArrayList<ParkingDataModel>();

            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                JSONArray jarry = obj.getJSONArray("carparks");
                // jarry holds all of the lots
                ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> m_li;

                for (int i = 0; i < jarry.length(); i++) {
                    JSONObject jo_inside = jarry.getJSONObject(i);

                    Integer id = Integer.parseInt(jo_inside.getString("id"));
                    String adr = jo_inside.getString("address");
                    Float lat = Float.parseFloat(jo_inside.getString("lat"));
                    Float lng = Float.parseFloat(jo_inside.getString("lng"));
                    String rhh = jo_inside.getString("rate");
                    String cpt = jo_inside.getString("carpark_type");
                    String cpts = jo_inside.getString("carpark_type_str");
                    Boolean ttc = Boolean.parseBoolean(jo_inside.getString("is_ttc"));
                    Double ratehh = Double.parseDouble(jo_inside.getString("rate_half_hour"));
                    Integer cap = Integer.parseInt(jo_inside.getString("capacity"));
                    String maxhet = jo_inside.getString("max_height");
                    maxhet.replace(".", "'");
                    maxhet += '"';

                    JSONArray pmJSON = jo_inside.getJSONArray("payment_methods");
                    ArrayList<String> pm = new ArrayList<String>();
                    for (Integer ii = 0; ii < pmJSON.length(); ii++) {
                        pm.add(pmJSON.getString(ii));
                    }

                    JSONArray posJSON = jo_inside.getJSONArray("payment_options");
                    ArrayList<String> pos = new ArrayList<String>();
                    for (Integer ii = 0; ii < posJSON.length(); ii++) {
                        pos.add(posJSON.getString(ii));
                    }

                    //---------------- RATE DETAILS --------------------\\
                    JSONObject rdJSON = jo_inside.getJSONObject("rate_details");
                    // This is a rather complicated entry and requires further breakdown
                    String title;
                    HashMap<String, String> rates = new HashMap<String, String>();
                    ArrayList<String> notes = new ArrayList<String>();
                    ArrayList<String> addenda = new ArrayList<String>();

                    JSONObject periods = rdJSON.getJSONArray("periods").getJSONObject(0);
                    title = periods.getString("title");

                    JSONArray rts = periods.getJSONArray("rates");
                    for (int iii = 0; iii < rts.length(); iii++) {
                        JSONObject rts_details_map = rts.getJSONObject(0); //Something is fishy here.
                        rates.put(rts_details_map.getString("when"), rts_details_map.getString("rate"));
                    }

                    JSONArray nts = periods.getJSONArray("notes");
                    for (int iii = 0; iii < nts.length(); iii++) {
                        notes.add(nts.getString(iii));
                    }

                    JSONArray ads = rdJSON.getJSONArray("addenda");
                    for (int iii = 0; iii < ads.length(); iii++) {
                        addenda.add(ads.getString(iii));
                    }
                    //--------------END-------------\\


                    //-Streetview data-\\
                    String esvs = jo_inside.getString("enable_streetview");
                    Boolean esv;
                    if (esvs.equals("yes")) {
                        esv = true;
                    } else {
                        esv = false;
                    }
                    Float svlat = Float.parseFloat(jo_inside.getString("streetview_lat"));
                    Float svlng = Float.parseFloat(jo_inside.getString("streetview_long"));
                    Float svyaw = Float.parseFloat(jo_inside.getString("streetview_yaw"));
                    Float svpit = Float.parseFloat(jo_inside.getString("streetview_pitch"));
                    Float svzom = Float.parseFloat(jo_inside.getString("streetview_zoom"));
                    Log.d("Details :", jarry.getString(i));

                    this.data.add(new ParkingDataModel(i, ttc, esv, id, cap, adr, rhh, cpt, cpts, maxhet, esvs, title, lat, lng, svlat, svlng, svyaw, svpit, svzom, ratehh, pm, pos, notes, addenda, rates, location));
                }
            } catch (JSONException e) {
                Log.d("Exception parsing JSON: ", e.getMessage());
            }

            Log.i("JSON LOAD COMPLETE", "TOTAL DATA SIZE:" + data.size());
        }
            LAST_POSITION = data.size();
        }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Bundle getItem(int position){
        Bundle bundle = new Bundle();
        bundle.putString("Address", data.get(position).getAdr());
        bundle.putString("RateHH", data.get(position).getRhh());
        bundle.putString("CarParkType", data.get(position).getCpt());
        bundle.putString("MaxHeight", data.get(position).getMaxhet());
        bundle.putString("Title", data.get(position).getTitle());
        bundle.putString("TTC", data.get(position).getTtc().toString());
        bundle.putString("Payment Methods", data.get(position).getPm());
        bundle.putString("Distance", data.get(position).getDistance());
        bundle.putString("Eta", data.get(position).getEta());
        bundle.putString("Rates", data.get(position).getRates());
        bundle.putStringArrayList("Notes", data.get(position).getNotes());
        bundle.putStringArrayList("POS", data.get(position).getPos());

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
            adr.setText(data.get(position).getAdr());

            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(data.get(position).getLat(), data.get(position).getLng(), 1);
            if(addresses.size() > 0){
                city.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getPostalCode());
            }
            else{
                Log.d("Unable to find city: ","Lng: " + data.get(position).getLng() + " | Lat: " + data.get(0).getLat());
            }

            //destination calculation
            dist.setText(data.get(position).getDistance());
            eta.setText(data.get(position).getEta());


            //-Set Button Click-\\

            map.setOnClickListener(new CustomOnClickListener(data.get(position).getLat(), data.get(position).getLng(), (float)this.location.getLatitude(), (float)this.location.getLongitude(), this.context));

            rate.setText(data.get(position).getRhh());
            pymt.setText(data.get(position).getPm());

        }catch(IOException e){
            Log.d("IOException while getting view: ", e.getMessage());
        }
        return vi;
    }

    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = context.getAssets().open("greenp-open-data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch(IOException ex){
            Log.d("IOError, printing message: ",ex.getMessage());
            return null;
        }
        return json;
    }

    public static void shakeData(){
        Collections.sort(data, ParkingDataModel.Comparators.ETA);

    }
}


