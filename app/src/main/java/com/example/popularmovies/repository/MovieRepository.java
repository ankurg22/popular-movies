package com.example.popularmovies.repository;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.ApiResponse;
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
        mApiClient.getTopRatedMovies().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
        return data;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        mApiClient.getPopularMovies().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
        return data;
    }
}
