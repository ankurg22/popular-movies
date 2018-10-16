package com.example.popularmovies.app;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import static com.example.popularmovies.Constants.URL_BACKDROP;
import static com.example.popularmovies.Constants.URL_POSTER;

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
            mMovie = intent.getParcelableExtra(KEY_EXTRA_MOVIE);
        }

        initializeViews();

        populateViews();
    }

    private void populateViews() {
        if (mMovie != null) {
            Picasso p = Picasso.get();

            if (mMovie.getBackdropPath() != null)
                p.load(URL_BACKDROP + mMovie.getBackdropPath())
                        .into(mBackgroundImage);
            if (mMovie.getPosterPath() != null)
                p.load(URL_POSTER + mMovie.getPosterPath())
                        .into(mPosterImage);

            if (mMovie.getOriginalTitle() != null)
                mTitleText.setText(mMovie.getOriginalTitle());
            if (mMovie.getReleaseDate() != null)
                mReleaseText.setText(mMovie.getReleaseDate());
            if (mMovie.getOverview() != null)
                mDescriptionText.setText(mMovie.getOverview());

            if (mMovie.getVoteAverage() != null)
                mRating.setRating(Float.valueOf(mMovie.getVoteAverage()));
        }
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
