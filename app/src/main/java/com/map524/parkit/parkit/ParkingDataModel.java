package com.map524.parkit.parkit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ParkingDataModel {

    private Boolean is_ttc;

    private Integer id;

    private Double lat;
    private Double lng;
    private Double rate_half_hour;
    private Double streetview_lat;
    private Double streetview_long;
    private Double streetview_yaw;
    private Double streetview_pitch;
    private Double streetview_zoom;

    private String address;
    private String rate;
    private String carpark_type;
    private String carpark_type_str;
    private String max_height;

    private String[] title;
    private String[] rates;
    private String[] rateA;
    private String[] notes;
    private String[] addenda;


    public ParkingDataModel(Boolean is_ttc, Integer id, Double lat, Double lng, Double rate_half_hour, Double streetview_lat, Double streetview_long, Double streetview_yaw, Double streetview_pitch,
                            Double streetview_zoom, String address, String rate, String carpark_type, String carpark_type_str, String max_height, String[] title, String[] rates,
                            String[] rateA, String[] notes, String[] addenda) {
        this.is_ttc = is_ttc;
        this.id = id;

        this.lat = lat;
        this.lng = lng;
        this.rate_half_hour = rate_half_hour;
        this.streetview_lat = streetview_lat;
        this.streetview_long = streetview_long;
        this.streetview_yaw = streetview_yaw;
        this.streetview_pitch = streetview_pitch;
        this.streetview_zoom = streetview_zoom;

        this.address = address;
        this.rate = rate;
        this.carpark_type = carpark_type;
        this.carpark_type_str = carpark_type_str;
        this.max_height = max_height;

        this.title = title;
        this.rates = rates;
        this.rateA = rateA;
        this.notes = notes;
        this.addenda = addenda;
    }

    public Boolean getIs_ttc() {
        return is_ttc;
    }

    public void setIs_ttc(Boolean is_ttc) {
        this.is_ttc = is_ttc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getRate_half_hour() {
        return rate_half_hour;
    }

    public void setRate_half_hour(Double rate_half_hour) {
        this.rate_half_hour = rate_half_hour;
    }

    public Double getStreetview_lat() {
        return streetview_lat;
    }

    public void setStreetview_lat(Double streetview_lat) {
        this.streetview_lat = streetview_lat;
    }

    public Double getStreetview_long() {
        return streetview_long;
    }

    public void setStreetview_long(Double streetview_long) {
        this.streetview_long = streetview_long;
    }

    public Double getStreetview_yaw() {
        return streetview_yaw;
    }

    public void setStreetview_yaw(Double streetview_yaw) {
        this.streetview_yaw = streetview_yaw;
    }

    public Double getStreetview_pitch() {
        return streetview_pitch;
    }

    public void setStreetview_pitch(Double streetview_pitch) {
        this.streetview_pitch = streetview_pitch;
    }

    public Double getStreetview_zoom() {
        return streetview_zoom;
    }

    public void setStreetview_zoom(Double streetview_zoom) {
        this.streetview_zoom = streetview_zoom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCarpark_type() {
        return carpark_type;
    }

    public void setCarpark_type(String carpark_type) {
        this.carpark_type = carpark_type;
    }

    public String getCarpark_type_str() {
        return carpark_type_str;
    }

    public void setCarpark_type_str(String carpark_type_str) {
        this.carpark_type_str = carpark_type_str;
    }

    public String getMax_height() {
        return max_height;
    }

    public void setMax_height(String max_height) {
        this.max_height = max_height;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getRates() {
        return rates;
    }

    public void setRates(String[] rates) {
        this.rates = rates;
    }

    public String[] getRateA() {
        return rateA;
    }

    public void setRateA(String[] rateA) {
        this.rateA = rateA;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public String[] getAddenda() {
        return addenda;
    }

    public void setAddenda(String[] addenda) {
        this.addenda = addenda;
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