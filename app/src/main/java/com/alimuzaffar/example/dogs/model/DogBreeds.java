package com.alimuzaffar.example.dogs.model;

import android.util.Log;

import com.alimuzaffar.example.dogs.net.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogBreeds extends BaseObservable {
    String status;
    List<DogBreed> breedsList = new ArrayList<>();
    MutableLiveData<List<DogBreed>> breeds = new MutableLiveData<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addBreed(DogBreed bd) {
        breedsList.add(bd);
    }

    public MutableLiveData<List<DogBreed>> getBreeds() {
        return breeds;
    }

    public List<DogBreed> getBreedsList() {
        return breedsList;
    }

    public void fetchList() {
        Callback<DogBreeds> callback = new Callback<DogBreeds>() {
            @Override
            public void onResponse(Call<DogBreeds> call, Response<DogBreeds> response) {
                DogBreeds body = response.body();
                status = body.status;
                breeds.setValue(body.breedsList);
            }

            @Override
            public void onFailure(Call<DogBreeds> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getBreeds().enqueue(callback);
    }
}
