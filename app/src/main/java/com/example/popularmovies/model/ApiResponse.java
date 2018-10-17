package com.example.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<T> {

    @SerializedName("results")
    private List<T> results = null;

    public ApiResponse() {
    }

    public ApiResponse(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
