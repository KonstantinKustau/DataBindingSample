package com.example.ibanez_xiphos.databindingsample.model;

public class Counters {

    private int photos;
    private int friends;
    private int stars;

    public Counters(int photos, int friends, int stars) {
        this.photos = photos;
        this.friends = friends;
        this.stars = stars;
    }

    public int getPhotos() {
        return photos;
    }

    public int getFriends() {
        return friends;
    }

    public int getStars() {
        return stars;
    }
}
