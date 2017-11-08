package com.map524.parkit.parkit;

import java.util.ArrayList;

public class ParkingDataModel{
    private Integer id;
    private Double lat;
    private Double lng;
    private Double max_height;
    private String rate;
    private String address;
    private String carpark_type;
    private String carpark_desc; // carpark_type_str
    private String payment_methods;
    private String payment_options;
    private String rate2; // Unknown variable, row22

    public ParkingDataModel(Integer id, Double lat, Double lng, String rate, Double max_height, String address, String carpark_type, String carpark_desc, String payment_methods, String payment_options, String rate2){
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.rate = rate;
        this.max_height = max_height;
        this.address = address;
        this.carpark_desc = carpark_desc;
        this.carpark_type = carpark_type;
        this.payment_methods = payment_methods;
        this.payment_options = payment_options;
        this.rate2 = rate2;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getMax_height() {
        return max_height;
    }

    public void setMax_height(Double max_height) {
        this.max_height = max_height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarpark_type() {
        return carpark_type;
    }

    public void setCarpark_type(String carpark_type) {
        this.carpark_type = carpark_type;
    }

    public String getCarpark_desc() {
        return carpark_desc;
    }

    public void setCarpark_desc(String carpark_desc) {
        this.carpark_desc = carpark_desc;
    }

    public String getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(String payment_methods) {
        this.payment_methods = payment_methods;
    }

    public String getPayment_options() {
        return payment_options;
    }

    public void setPayment_options(String payment_options) {
        this.payment_options = payment_options;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }
}
/*
* Incoming Array data structure
* 0 - Id
* 1 - address
* 2 - lat
* 3 - lng
* 4 - rate
* 5 - rate_half_hour
* 6 - carpark_type
* 7 - carpark_type_str
* 8 - capacity
* 9 - max_height
* 10 - payment_methods
* 11 - payment_options
* 12 - rate_details
* 13 - periods
* 14 - title
* 15 - rates
* 16 - notes
* 17 - addenda
* 18 - enable_streetview
* 19 - streetview_lat
* 20 - streetview_lng
* 21 - streetview_yaw
* 22 - streetview_pitch
* 23 - streetview_zoom
*/