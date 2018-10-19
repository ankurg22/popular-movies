package com.example.popularmovies.data;

import com.example.popularmovies.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getFavorites();

    @Insert
    void insertFavorite(Movie... movies);

    @Delete
    void deleteFavorite(Movie movie);

    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    LiveData<Movie> isFavorite(String id);

}
