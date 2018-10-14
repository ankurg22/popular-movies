package com.example.popularmovies.app;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_MOVIE = "key_movie";
    private Movie mMovie;

    private ImageView mPosterImage;
    private ImageView mBackgroundImage;
    private TextView mTitleText;
    private TextView mReleaseText;
    private TextView mDescriptionText;
    private RatingBar mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_EXTRA_MOVIE)) {
            mMovie = (Movie) intent.getSerializableExtra(KEY_EXTRA_MOVIE);
        }

        initializeViews();

        populateViews();
    }

    private void populateViews() {
        Picasso p = Picasso.get();
        p.load("http://image.tmdb.org/t/p/w780" + mMovie.getBackdropPath())
                .into(mBackgroundImage);
        p.load("http://image.tmdb.org/t/p/w342" + mMovie.getPosterPath())
                .into(mPosterImage);

        mTitleText.setText(mMovie.getOriginalTitle());
        mReleaseText.setText(mMovie.getReleaseDate());
        mDescriptionText.setText(mMovie.getOverview());

        mRating.setRating(Float.valueOf(mMovie.getVoteAverage()));
    }

    private void initializeViews() {
        mPosterImage = findViewById(R.id.iv_detail_poster);
        mBackgroundImage = findViewById(R.id.iv_detail_background);
        mTitleText = findViewById(R.id.tv_detail_title);
        mReleaseText = findViewById(R.id.tv_detail_release);
        mDescriptionText = findViewById(R.id.tv_detail_description);
        mRating = findViewById(R.id.rb_detatil_rating);
    }
}
