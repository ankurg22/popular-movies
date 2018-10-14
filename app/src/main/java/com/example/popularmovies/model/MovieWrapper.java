package com.example.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieWrapper {

    @SerializedName("results")
    private List<Movie> movies = null;

    public MovieWrapper() {
    }

    public MovieWrapper(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
