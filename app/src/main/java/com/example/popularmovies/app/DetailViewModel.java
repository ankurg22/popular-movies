package com.example.popularmovies.app;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Trailer;
import com.example.popularmovies.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {
    private Movie mMovie;
    private LiveData<List<Trailer>> mTrailers;
    private MovieRepository mMovieRepo;

    @Inject
    public DetailViewModel(MovieRepository movieRepo) {
        this.mMovieRepo = movieRepo;
    }

    public void setMovie(Movie mMovie) {
        this.mMovie = mMovie;
    }

    public LiveData<List<Trailer>> getTrailers() {
        return mMovieRepo.getTrailers(mMovie.getId());
    }
}
