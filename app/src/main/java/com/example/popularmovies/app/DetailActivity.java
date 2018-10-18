package com.example.popularmovies.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import dagger.android.AndroidInjection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.app.adapter.OnItemClickListener;
import com.example.popularmovies.app.adapter.ReviewAdapter;
import com.example.popularmovies.app.adapter.TrailerAdapter;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import static com.example.popularmovies.Constants.URL_BACKDROP;
import static com.example.popularmovies.Constants.URL_POSTER;

public class DetailActivity extends AppCompatActivity implements OnItemClickListener {
    public static final String KEY_EXTRA_MOVIE = "key_movie";
    private Movie mMovie;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private DetailViewModel mDetailViewModel;

    private ImageView mPosterImage;
    private ImageView mBackgroundImage;
    private TextView mTitleText;
    private TextView mReleaseText;
    private TextView mDescriptionText;
    private RatingBar mRating;

    private RecyclerView mTrailerRecycler;
    private RecyclerView mReviewRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        AndroidInjection.inject(this);
        mDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_EXTRA_MOVIE)) {
            mMovie = intent.getParcelableExtra(KEY_EXTRA_MOVIE);
            mDetailViewModel.setMovie(mMovie);
            mDetailViewModel.init();
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

        mDetailViewModel.getTrailers().observe(this, trailers -> {
            TrailerAdapter adapter = new TrailerAdapter(trailers, this);
            mTrailerRecycler.setAdapter(adapter);
        });

        mDetailViewModel.getReviews().observe(this, reviews -> {
            ReviewAdapter adapter = new ReviewAdapter(reviews);
            mReviewRecycler.setAdapter(adapter);
        });

    }

    private void initializeViews() {
        mPosterImage = findViewById(R.id.iv_detail_poster);
        mBackgroundImage = findViewById(R.id.iv_detail_background);
        mTitleText = findViewById(R.id.tv_detail_title);
        mReleaseText = findViewById(R.id.tv_detail_release);
        mDescriptionText = findViewById(R.id.tv_detail_description);
        mRating = findViewById(R.id.rb_detatil_rating);

        mTrailerRecycler = findViewById(R.id.rv_detail_trailer);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL);
        mTrailerRecycler.setLayoutManager(layoutManager);

        mReviewRecycler = findViewById(R.id.rv_detail_review);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        mReviewRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClick(Object object) {
        if (object instanceof Trailer) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("vnd.youtube://" + ((Trailer) object).getKey()));
            startActivity(intent);
        }
    }
}
