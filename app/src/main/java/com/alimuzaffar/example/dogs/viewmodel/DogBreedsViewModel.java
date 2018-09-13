package com.alimuzaffar.example.dogs.viewmodel;

import com.alimuzaffar.example.dogs.R;
import com.alimuzaffar.example.dogs.adapter.DogBreedsAdapter;
import com.alimuzaffar.example.dogs.model.DogBreed;
import com.alimuzaffar.example.dogs.model.DogBreeds;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DogBreedsViewModel extends ViewModel {

    private DogBreeds dogBreeds;
    private DogBreedsAdapter adapter;
    public MutableLiveData<DogBreed> selected;

    public void init() {
        dogBreeds = new DogBreeds();
        selected = new MutableLiveData<>();
        adapter = new DogBreedsAdapter(R.layout.view_dog_breed, this);
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

    public void onItemClick(DogBreed dogBreed) {
        selected.setValue(dogBreed);
    }
}
