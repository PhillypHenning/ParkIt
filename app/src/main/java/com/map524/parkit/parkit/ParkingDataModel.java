package com.map524.parkit.parkit;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class ParkingDataModel {

    private Boolean ttc;
    private Boolean esv;

    private Integer id;
    private Integer cap;
    private Integer position;

    private String adr;
    private String rhh;
    private String cpt;
    private String cpts;
    private String maxhet;
    private String esvs;
    private String title;
    private String distance;
    private String eta;

    private Float lat;
    private Float lng;
    private Float svlat;
    private Float svlng;
    private Float svyaw;
    private Float svpit;
    private Float svzom;

    private Double ratehh;

    private ArrayList<String> pm;
    private ArrayList<String> pos;
    private ArrayList<String> notes;
    private ArrayList<String> addenda;

    private HashMap<String, String> rates;
    Location location;


    public ParkingDataModel(int position, Boolean ttc, Boolean esv, Integer id, Integer cap, String adr, String rhh, String cpt, String cpts, String maxhet, String esvs, String title, Float lat, Float lng, Float svlat, Float svlng, Float svyaw, Float svpit, Float svzom, Double ratehh, ArrayList<String> pm, ArrayList<String> pos, ArrayList<String> notes, ArrayList<String> addenda, HashMap<String, String> rates, Location location) {
        this.position = position;

        this.ttc = ttc;
        this.esv = esv;

        this.id = id;
        this.cap = cap;

        this.adr = adr;
        this.rhh = rhh;
        this.cpt = cpt;
        this.cpts = cpts;
        this.maxhet = maxhet;
        this.esvs = esvs;
        this.title = title;

        this.lat = lat;
        this.lng = lng;
        this.svlat = svlat;
        this.svlng = svlng;
        this.svyaw = svyaw;
        this.svpit = svpit;
        this.svzom = svzom;

        this.ratehh = ratehh;

        this.pm = pm;
        this.pos = pos;
        this.notes = notes;
        this.addenda = addenda;

        this.rates = rates;
        this.location = location;
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + lat.toString() + "," + lng.toString() + "&destinations=" + this.location.getLatitude() + "," + this.location.getLongitude() + "&units=metric&key=AIzaSyCaWJOZSIN6wEaCnelo-SrlYgNz-6NN3oQ");
            if(position < 10) {
                new Directions().execute(url).get();
            }else{
                new Directions().execute(url);
            }
        }catch(MalformedURLException e){
            Log.d("Malformed URL exception: ", e.getMessage());
        }catch(InterruptedException e){

        }catch (ExecutionException e){

        }
    }

    public ParkingDataModel(){
        // Stub constructor, come back to this and change the catch in the parse to create one of these in case of fail.

    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        ParkingDataModel.this.distance = distance;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public Boolean getTtc() {
        return ttc;
    }

    public Boolean getEsv() {
        return esv;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCap() {
        return cap;
    }

    public String getAdr() {
        return adr;
    }

    public String getRhh() {
        return rhh;
    }

    public String getCpt() {
        return cpt;
    }

    public String getCpts() {
        return cpts;
    }

    public String getMaxhet() {
        return maxhet;
    }

    public String getEsvs() {
        return esvs;
    }

    public String getTitle() {
        return title;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public Float getSvlat() {
        return svlat;
    }

    public Float getSvlng() {
        return svlng;
    }

    public Float getSvyaw() {
        return svyaw;
    }

    public Float getSvpit() {
        return svpit;
    }

    public Float getSvzom() {
        return svzom;
    }

    public Double getRatehh() {
        return ratehh;
    }

    public Integer getPosition(){ return position; }

    public String getPm() {
        String result = new String();
        StringBuilder JavaIsOverlyComplicated = new StringBuilder();
        for(String item : this.pm){
            if(item.equals("Bills")){
                JavaIsOverlyComplicated.append("Cash | ");
            }
            else if(item.contains("Visa") || item.contains("Mastercard") || item.contains("American Express")){
                JavaIsOverlyComplicated.append("Credit | ");
            }
        }
        if(JavaIsOverlyComplicated.length() == 0){
            JavaIsOverlyComplicated.append("Free");
        }
        JavaIsOverlyComplicated.deleteCharAt(JavaIsOverlyComplicated.lastIndexOf("|"));
        return JavaIsOverlyComplicated.toString();
    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public ArrayList<String> getAddenda() {
        return addenda;
    }

    public String getRates() {
        return "test";
    }



    private class Directions extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls){
            System.out.println(urls[0]);
            String result = new String();
            try{//call the server
                HttpsURLConnection conn = (HttpsURLConnection) urls[0].openConnection();

                //Create SSL Certificates
                SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                sc.init(null,null, new java.security.SecureRandom());
                conn.setSSLSocketFactory(sc.getSocketFactory());

                // set Timeout
                conn.setReadTimeout(7000);
                conn.setConnectTimeout(7000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();

                InputStream is = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String inputLine;
                while((inputLine = in.readLine()) != null){
                    result += inputLine;
                }

                in.close();
                is.close();

            }catch(NoSuchAlgorithmException e){
                Log.d("SSL cert get Instance failed: ", e.getMessage());
            }catch(KeyManagementException e){
                Log.d("SSL cert init failed:", e.getMessage());
            }
            catch (MalformedURLException e){
                Log.d("Malformed URL:", urls.toString());
            }catch(IOException e){
                Log.d("IOException while getting DistanceMatrix: ", e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String message){
            // process message
            try{
                JSONObject obj = new JSONObject(message);
                JSONArray jarry = obj.getJSONArray("rows");
                JSONArray jo_inside = jarry.getJSONObject(0).getJSONArray("elements");
                JSONObject elements = jo_inside.getJSONObject(0);
                setDistance(elements.getJSONObject("distance").getString("text"));
                setEta(elements.getJSONObject("duration").getString("text"));


            }catch(JSONException e){
                Log.d("JSONException while converting inner class direction message", e.getMessage());
            }

            if (getPosition() == NearbyLotAdapter.LAST_POSITION -1){
                // resort data
                NearbyLotAdapter.shakeData();
            }
        }
    }

    public static class Comparators {
        public static Comparator<ParkingDataModel> DIST = new Comparator<ParkingDataModel>(){
            @Override
            public int compare(ParkingDataModel pdm1, ParkingDataModel pdm2){
                return pdm1.getDistance().compareTo(pdm2.getDistance());
            }
        };
        public static Comparator<ParkingDataModel> ETA = new Comparator<ParkingDataModel>(){
            @Override
            public int compare(ParkingDataModel pdm1, ParkingDataModel pdm2){
                return pdm1.getEta().compareTo(pdm2.getEta());
            }
        };
        public static Comparator<ParkingDataModel> COST = new Comparator<ParkingDataModel>(){
            @Override
            public int compare(ParkingDataModel pdm1, ParkingDataModel pdm2){
                // Extra work if time
                // Parse rate, compare

                return pdm1.getDistance().compareTo(pdm2.getDistance());
            }
        };
    }
}


/*
* GreenP Data structure
* 0 - Id                    | Integer
* 1 - address               | String
* 2 - lat                   | Double
* 3 - lng                   | Double
* 4 - rate_half_hour        | String
* 5 - carpark_type          | String
* 6 - carpark_type_str      | String
* 7 - is_ttc                | Boolean
* 8 - rate_half_hour        | Double
* 9 - capacity              | Long
* 10 - max_height           | String
* 11 - payment_methods      | Array<String>
* 12 - payment_options      | Array<String>
* 13 - rate_details         | Dict<Array>
*   13-0 - title            | Array<String>
*   13-1 - rates            | Array<String>
*   13-2   rate             | Array<String>
*   13-3   notes            | Array<String>
*   13-4   addenda          | Array<String>
* 14 - enable_streetview    | Boolean
* 15 - streetview_lat       | Double
* 16 - streetview_long      | Double
* 17 - streetview_yaw       | Double
* 18 - streetview_pitch     | Double
* 19 - streetview_zoom      | Double
*/