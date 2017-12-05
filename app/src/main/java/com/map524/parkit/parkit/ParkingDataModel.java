package com.map524.parkit.parkit;

import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ParkingDataModel {

    private Boolean ttc;
    private Boolean esv;

    private Integer id;
    private Integer cap;

    private String adr;
    private String rhh;
    private String cpt;
    private String cpts;
    private String maxhet;
    private String esvs;
    private String title;

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


    public ParkingDataModel(Boolean ttc, Boolean esv, Integer id, Integer cap, String adr, String rhh, String cpt, String cpts, String maxhet, String esvs, String title, Float lat, Float lng, Float svlat, Float svlng, Float svyaw, Float svpit, Float svzom, Double ratehh, ArrayList<String> pm, ArrayList<String> pos, ArrayList<String> notes, ArrayList<String> addenda, HashMap<String, String> rates) {
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
    }

    public ParkingDataModel(){
        // Stub constructor, come back to this and change the catch in the parse to create one of these in case of fail.

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

    public ArrayList<String> getPm() {
        return pm;
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

    public HashMap<String, String> getRates() {
        return rates;
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