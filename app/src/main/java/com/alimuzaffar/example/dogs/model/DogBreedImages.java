package com.alimuzaffar.example.dogs.model;

import com.google.gson.annotations.SerializedName;

public class DogBreedImages {
    private String status;
    @SerializedName("message")
    private String [] images;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
