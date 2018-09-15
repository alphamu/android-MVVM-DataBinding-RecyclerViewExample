package com.alimuzaffar.example.dogs.net;

import com.alimuzaffar.example.dogs.model.DogBreedImages;

import retrofit2.Callback;

public abstract class DogImagesCallback implements Callback<DogBreedImages> {
    private String breed;

    public DogImagesCallback(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return this.breed;
    }

}
