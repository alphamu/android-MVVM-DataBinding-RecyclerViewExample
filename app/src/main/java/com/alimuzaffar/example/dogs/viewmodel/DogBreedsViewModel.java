package com.alimuzaffar.example.dogs.viewmodel;

import android.util.Log;
import android.view.View;

import com.alimuzaffar.example.dogs.R;
import com.alimuzaffar.example.dogs.adapter.DogBreedsAdapter;
import com.alimuzaffar.example.dogs.model.DogBreed;
import com.alimuzaffar.example.dogs.model.DogBreedImages;
import com.alimuzaffar.example.dogs.model.DogBreeds;
import com.alimuzaffar.example.dogs.net.DogImagesCallback;

import java.util.List;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Response;

public class DogBreedsViewModel extends ViewModel {

    private DogBreeds dogBreeds;
    private DogBreedsAdapter adapter;
    public MutableLiveData<DogBreed> selected;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init() {
        dogBreeds = new DogBreeds();
        selected = new MutableLiveData<>();
        adapter = new DogBreedsAdapter(R.layout.view_dog_breed, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void fetchList() {
        dogBreeds.fetchList();
    }

    public MutableLiveData<List<DogBreed>> getBreeds() {
        return dogBreeds.getBreeds();
    }

    public DogBreedsAdapter getAdapter() {
        return adapter;
    }

    public void setDogBreedsInAdapter(List<DogBreed> breeds) {
        this.adapter.setDogBreeds(breeds);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<DogBreed> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        DogBreed db = getDogBreedAt(index);
        selected.setValue(db);
    }

    public DogBreed getDogBreedAt(Integer index) {
        if (dogBreeds.getBreeds().getValue() != null &&
                index != null &&
                dogBreeds.getBreeds().getValue().size() > index) {
            return dogBreeds.getBreeds().getValue().get(index);
        }
        return null;
    }

    public void fetchDogBreedImagesAt(Integer index) {
        DogBreed dogBreed = getDogBreedAt(index);
        if (dogBreed != null && !images.containsKey(dogBreed.getBreed())) {
            dogBreed.fetchImages(new DogImagesCallback(dogBreed.getBreed()) {
                @Override
                public void onResponse(Call<DogBreedImages> call, Response<DogBreedImages> response) {
                    DogBreedImages body = response.body();
                    if (body.getImages() != null && body.getImages().length > 0) {
                        String thumbnailUrl = body.getImages()[0];
                        images.put(getBreed(), thumbnailUrl);
                    }
                }

                @Override
                public void onFailure(Call<DogBreedImages> call, Throwable t) {
                    Log.e("Test", t.getMessage(), t);
                }
            });
        }
    }


}
