package com.example.popularmovies.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private LiveData<List<Movie>> mTopRatedMovies;
    private MovieRepository mMovieRepo;

    @Inject
    public MainViewModel(MovieRepository movieRepo) {
        this.mMovieRepo = movieRepo;
    }

    public void init() {
        if (this.mTopRatedMovies != null) {
            return;
        }
        mTopRatedMovies = mMovieRepo.getTopRatedMovies();
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return mTopRatedMovies;
    }
}
