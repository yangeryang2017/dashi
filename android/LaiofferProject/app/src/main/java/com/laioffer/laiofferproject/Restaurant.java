package com.laioffer.laiofferproject;

import android.graphics.Bitmap;

/**
 * A class for restaurant, which contains all information of a restaurant.
 */
public class Restaurant {

    private String businessid;
    private String name;
    private String address;
    private String type;
    private double lat;
    private double lng;
    private Bitmap thumbnail;




    /**
     * Constructor
     *
     * @param name name of the restaurant
     */
    public Restaurant(String businessid, String name, String address, String type, double lat, double lng,
                      Bitmap thumbnail) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
        this.businessid = businessid;
        this.thumbnail = thumbnail;
    }

    /**
     * Getters for private attributes of Restaurant class.
     */
    public String getName() { return this.name; }

    public String getAddress() { return this.address; }

    public String getType() { return this.type; }

    public double getLat() { return lat; }

    public double getLng() { return lng; }

    public String getBusinessId() { return businessid; }

    public Bitmap getThumbnail() { return thumbnail; }
}