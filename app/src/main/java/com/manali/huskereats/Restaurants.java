package com.manali.huskereats;

//Class used to define the restaurant object for recycler view
public class Restaurants {

    private String restaurant_name;
    private int thumbnail;

    public Restaurants() {
    }

    public Restaurants(String restaurant_name, int thumbnail) {
        this.restaurant_name = restaurant_name;
        this.thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
