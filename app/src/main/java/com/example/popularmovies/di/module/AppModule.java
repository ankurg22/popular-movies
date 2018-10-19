package com.example.popularmovies.di.module;

import android.app.Application;

import com.example.popularmovies.BuildConfig;
import com.example.popularmovies.data.AppDatabase;
import com.example.popularmovies.data.MovieDao;
import com.example.popularmovies.remote.ApiClient;
import com.example.popularmovies.repository.MovieRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    MovieRepository provideUserRepository(ApiClient apiClient, MovieDao movieDao, Executor executor) {
        return new MovieRepository(apiClient, movieDao, executor);
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, AppDatabase.DATABASE_NAME)
                .build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(AppDatabase appDatabase) {
        return appDatabase.movieDao();
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    HttpUrl url = chain.request().url().newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .build();
                    Request.Builder requestBuilder = chain.request().newBuilder().url(url);
                    return chain.proceed(requestBuilder.build());
                })
                .build();
    }

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}
