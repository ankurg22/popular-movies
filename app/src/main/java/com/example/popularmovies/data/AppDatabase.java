package com.example.popularmovies.data;

import com.example.popularmovies.model.Movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "favorites";

    public abstract MovieDao movieDao();
}
