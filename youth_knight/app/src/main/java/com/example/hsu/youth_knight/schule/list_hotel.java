package com.example.hsu.youth_knight.schule;

/**
 * Created by hsu on 2017/2/5.
 */
public class list_hotel {
    String imageurl;
    String name;
    String address;
    String hotelurl;
    String hotel_discrible;

    public String getHotel_discrible() {
        return hotel_discrible;
    }

    public void setHotel_discrible(String hotel_discrible) {
        this.hotel_discrible = hotel_discrible;
    }

    public String getHotelurl() {

        return hotelurl;
    }

    public void setHotelurl(String hotelurl) {
        this.hotelurl = hotelurl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
