package com.example.popularmovies.repository;

import com.example.popularmovies.data.MovieDao;
import com.example.popularmovies.model.ApiResponse;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Trailer;
import com.example.popularmovies.remote.ApiClient;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MovieRepository {
    private final ApiClient mApiClient;
    private final MovieDao mMovieDao;
    private final Executor mExecutor;

    @Inject
    public MovieRepository(ApiClient apiClient, MovieDao movieDao, Executor executor) {
        mApiClient = apiClient;
        mMovieDao = movieDao;
        mExecutor = executor;
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        mApiClient.getTopRatedMovies().enqueue(new Callback<ApiResponse<Movie>>() {
            @Override
            public void onResponse(Call<ApiResponse<Movie>> call, Response<ApiResponse<Movie>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Movie>> call, Throwable t) {
            }
        });
        return data;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        mApiClient.getPopularMovies().enqueue(new Callback<ApiResponse<Movie>>() {
            @Override
            public void onResponse(Call<ApiResponse<Movie>> call, Response<ApiResponse<Movie>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Movie>> call, Throwable t) {
            }
        });
        return data;
    }

    public LiveData<List<Trailer>> getTrailers(String movieId) {
        MutableLiveData<List<Trailer>> data = new MutableLiveData<>();
        mApiClient.getTrailers(movieId).enqueue(new Callback<ApiResponse<Trailer>>() {
            @Override
            public void onResponse(Call<ApiResponse<Trailer>> call, Response<ApiResponse<Trailer>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Trailer>> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<List<Review>> getReviews(String movieId) {
        MutableLiveData<List<Review>> data = new MutableLiveData<>();
        mApiClient.getReviews(movieId).enqueue(new Callback<ApiResponse<Review>>() {
            @Override
            public void onResponse(Call<ApiResponse<Review>> call, Response<ApiResponse<Review>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Review>> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<Movie> isFavorite(Movie movie) {
        return mMovieDao.isFavorite(movie.getId());
    }

    public void addFavorite(Movie movie) {
        mExecutor.execute(() -> {
            boolean isFavorite = (mMovieDao.isFavorite(movie.getId()).getValue() != null);

            if (!isFavorite) {
                mMovieDao.insertFavorite(movie);
            }
        });
    }

    public void deleteFavorite(Movie movie) {
        mExecutor.execute(() -> mMovieDao.deleteFavorite(movie));
    }

    public LiveData<List<Movie>> getFavorites() {
        return mMovieDao.getFavorites();
    }
}
