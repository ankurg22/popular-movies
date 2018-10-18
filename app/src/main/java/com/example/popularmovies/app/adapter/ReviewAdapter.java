package com.example.popularmovies.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Review;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> mReviews;

    public ReviewAdapter(List<Review> mReviews) {
        this.mReviews = mReviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView mAuthorText;
        public TextView mContentText;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthorText = itemView.findViewById(R.id.tv_list_item_review_author);
            mContentText = itemView.findViewById(R.id.tv_list_item_review_content);
        }

        public void bind(Review review) {
            mAuthorText.setText(review.getAuthor());
            mContentText.setText(review.getContent());
        }
    }
}
