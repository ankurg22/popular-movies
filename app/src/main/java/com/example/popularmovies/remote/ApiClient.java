package com.example.popularmovies.remote;

import com.example.popularmovies.model.ApiResponse;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {

    @GET("movie/top_rated")
    Call<ApiResponse<Movie>> getTopRatedMovies();

    @GET("movie/popular")
    Call<ApiResponse<Movie>> getPopularMovies();

    @GET("/3/movie/{movie_id}/videos")
    Call<ApiResponse<Trailer>> getTrailers(@Path("movie_id") String movieId);

    @GET("/3/movie/{movie_id}/reviews")
    Call<ApiResponse<Review>> getReviews(@Path("movie_id") String movieId);
}
