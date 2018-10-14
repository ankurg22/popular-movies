package com.example.popularmovies.repository;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieWrapper;
import com.example.popularmovies.remote.ApiClient;

import java.util.List;

import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MovieRepository {
    private final ApiClient mApiClient;

    public MovieRepository(ApiClient apiClient) {
        mApiClient = apiClient;
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        mApiClient.getTopRatedMovies().enqueue(new Callback<MovieWrapper>() {
            @Override
            public void onResponse(Call<MovieWrapper> call, Response<MovieWrapper> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieWrapper> call, Throwable t) {
            }
        });
        return data;
    }
}
