package com.example.popularmovies.remote;

import com.example.popularmovies.model.MovieWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("movie/top_rated")
    Call<MovieWrapper> getTopRatedMovies();

    @GET("movie/popular")
    Call<MovieWrapper> getPopularMovies();
}
