package com.example.popularmovies.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private OnItemClickListener mListener;
    private List<Movie> mMovieList;

    public MovieAdapter(List<Movie> list, OnItemClickListener listener) {
        this.mMovieList = list;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_poster, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.bind(movie);
        holder.bindListener(movie, mListener);
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.iv_poster_item);
        }

        void bind(final Movie movie) {
            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w342" + movie.getPosterPath())
                    .placeholder(android.R.drawable.ic_media_play)
                    .into(posterImage);
        }

        void bindListener(Movie movie, OnItemClickListener listener) {
            posterImage.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
