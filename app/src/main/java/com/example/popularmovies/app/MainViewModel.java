package com.example.popularmovies.app;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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
                mMovies = mMovieRepo.getTopRatedMovies();
                break;
            case 1:
                mMovies = mMovieRepo.getPopularMovies();
                break;
            case 2:
                mMovies = mMovieRepo.getFavorites();
                break;
            default:
                mMovies = mMovieRepo.getPopularMovies();
                break;
        }
        return mMovies;
    }

    public void setListChoice(int choice) {
        mListChoice = choice;
    }
}
