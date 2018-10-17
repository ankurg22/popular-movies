package com.example.popularmovies.remote;

import com.example.popularmovies.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("movie/top_rated")
    Call<ApiResponse> getTopRatedMovies();

    @GET("movie/popular")
    Call<ApiResponse> getPopularMovies();
}
