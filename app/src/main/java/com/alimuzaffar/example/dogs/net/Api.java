package com.alimuzaffar.example.dogs.net;

import com.alimuzaffar.example.dogs.model.DogBreedImages;
import com.alimuzaffar.example.dogs.model.DogBreeds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Api {

    private static ApiInterface api;
    private static final String BASE_URL = "https://dog.ceo";

    public static ApiInterface getApi() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(
                            DogBreeds.class,
                            new JsonDogBreedsDeserializer())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET("/api/breeds/list/all")
        Call<DogBreeds> getBreeds();

        @GET("/api/breed/{breed}/images")
        Call<DogBreedImages> getImagesByBreed(@Path("breed") String breed);

    }
}