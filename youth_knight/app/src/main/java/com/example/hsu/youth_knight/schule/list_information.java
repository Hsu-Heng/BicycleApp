package com.example.hsu.youth_knight.schule;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by hsu on 2017/2/2.
 */
public class list_information {
    String day;
    String endtime;
    int whichschule;

    public int getWhichschule() {
        return whichschule;
    }

    public void setWhichschule(int whichschule) {
        this.whichschule = whichschule;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMovetime() {
        return movetime;
    }

    public void setMovetime(String movetime) {
        this.movetime = movetime;
    }

    public String getDistance() {

        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    String distance;
    String movetime;
    String name;
    String time;
    String address;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    LatLng latLng;
    String url;
    String phonenum;
    ArrayList<LatLng> street ;

    public ArrayList<LatLng> getStreet() {
        return street;
    }

    public void setStreet(ArrayList<LatLng> street) {
        this.street = street;
    }

    public LatLng getLatLng() {

        return latLng;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
