package com.example.popularmovies.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popularmovies.R;
import com.example.popularmovies.app.adapter.MovieAdapter;
import com.example.popularmovies.app.adapter.OnItemClickListener;
import com.example.popularmovies.model.Movie;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MovieAdapter mMovieAdapter;
    private MainViewModel mMainViewModel;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns());
        mRecyclerView = findViewById(R.id.movie_list);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mMainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        loadMovies();
    }

    private void loadMovies() {
        mMainViewModel.getMovies().observe(this, movies -> {
            mMovieAdapter = new MovieAdapter(movies, this);
            mRecyclerView.setAdapter(mMovieAdapter);
        });
    }

    private int numberOfColumns() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            if (width > 1000) {
                return 3;
            } else {
                return 2;
            }
        } else {
            if (width > 1700) {
                return 5;
            } else if (width > 1200) {
                return 4;
            } else {
                return 3;
            }
        }
    }

    @Override
    public void onItemClick(Object object) {
        if (object instanceof Movie) {
            Movie movie = (Movie) object;
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY_EXTRA_MOVIE, movie);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_pop_movies:
                mMainViewModel.setListChoice(1);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.main_top_movies:
                mMainViewModel.setListChoice(0);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.main_fav_movies:
                mMainViewModel.setListChoice(2);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
        }
        loadMovies();
        return super.onOptionsItemSelected(item);
    }
}
