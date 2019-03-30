package com.example.hsu.youth_knight.schule;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by hsu on 2017/2/3.
 */
public class list_section {
    String name;
    String address;
    LatLng lng;

    public LatLng getLng() {
        return lng;
    }

    public void setLng(LatLng lng) {
        this.lng = lng;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
