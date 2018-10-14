package com.example.popularmovies.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private LiveData<List<Movie>> mMovies;
    private MovieRepository mMovieRepo;
    private int mListChoice = 0;

    @Inject
    public MainViewModel(MovieRepository movieRepo) {
        this.mMovieRepo = movieRepo;
    }

    public LiveData<List<Movie>> getMovies() {
        switch (mListChoice) {
            case 0:
                return mMovieRepo.getTopRatedMovies();
            case 1:
                return mMovieRepo.getPopularMovies();
            default:
                return mMovieRepo.getPopularMovies();
        }
    }

    public void setListChoice(int choice) {
        mListChoice = choice;
    }
}
