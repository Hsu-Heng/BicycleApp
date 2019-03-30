package com.example.hsu.youth_knight.schule;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by hsu on 2017/2/6.
 */
public class list_routedata {
    ArrayList<LatLng> latLngs ;
    String name;
    String km;

    public String getName() {
        return name;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LatLng> getLatLngs() {
        return latLngs;

    }

    public void setLatLngs(ArrayList<LatLng> latLngs) {
        this.latLngs = latLngs;
    }
}
