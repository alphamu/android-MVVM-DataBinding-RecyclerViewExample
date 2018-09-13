package com.alimuzaffar.example.dogs;

import android.os.Bundle;
import android.widget.Toast;

import com.alimuzaffar.example.dogs.databinding.ActivityDogBreedsBinding;
import com.alimuzaffar.example.dogs.model.DogBreed;
import com.alimuzaffar.example.dogs.viewmodel.DogBreedsViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class DogBreedsActivity extends AppCompatActivity {
    private DogBreedsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_breeds);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityDogBreedsBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_dog_breeds);
        viewModel = ViewModelProviders.of(this).get(DogBreedsViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();

    }

    private void setupListUpdate() {
        viewModel.fetchList();
        viewModel.getBreeds().observe(this, new Observer<List<DogBreed>>() {
            @Override
            public void onChanged(List<DogBreed> dogBreeds) {
                viewModel.setDogBreedsInAdapter(dogBreeds);
            }
        });
        setupListClick();
    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<DogBreed>() {
            @Override
            public void onChanged(DogBreed dogBreed) {
                if (dogBreed != null) {
                    Toast.makeText(DogBreedsActivity.this, "You selected a " +dogBreed.getBreed(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
