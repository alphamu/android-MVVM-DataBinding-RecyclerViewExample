package com.alimuzaffar.example.dogs.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alimuzaffar.example.dogs.BR;
import com.alimuzaffar.example.dogs.model.DogBreed;
import com.alimuzaffar.example.dogs.viewmodel.DogBreedsViewModel;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DogBreedsAdapter extends RecyclerView.Adapter<DogBreedsAdapter.GenericViewHolder> {

    private int layoutId;
    private List<DogBreed> breeds;
    private DogBreedsViewModel viewModel;

    public DogBreedsAdapter(@LayoutRes int layoutId, DogBreedsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return breeds == null ? 0 : breeds.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setDogBreeds(List<DogBreed> breeds) {
        this.breeds = breeds;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DogBreedsViewModel viewModel, Integer position) {
            viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
