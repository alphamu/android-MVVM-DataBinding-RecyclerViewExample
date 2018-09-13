package com.alimuzaffar.example.dogs.model;

import android.util.Log;

import com.alimuzaffar.example.dogs.BR;
import com.alimuzaffar.example.dogs.net.Api;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogBreed extends BaseObservable {
    String breed;
    String thumbnailUrl;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
        this.thumbnailUrl = null;
        notifyPropertyChanged(BR.thumbnailUrl);
    }

    @Bindable
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void fetchImages() {
        if (thumbnailUrl != null) {
            return;
        }
        Callback<DogBreedImages> callback = new Callback<DogBreedImages>() {
            @Override
            public void onResponse(Call<DogBreedImages> call, Response<DogBreedImages> response) {
                DogBreedImages body = response.body();
                if (body.images != null && body.images.length > 0) {
                    thumbnailUrl = body.images[0];
                    notifyPropertyChanged(BR.thumbnailUrl);
                }
            }

            @Override
            public void onFailure(Call<DogBreedImages> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };
        Api.getApi().getImagesByBreed(this.breed).enqueue(callback);
    }
}
