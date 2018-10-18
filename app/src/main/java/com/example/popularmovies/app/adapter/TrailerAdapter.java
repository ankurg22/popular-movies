package com.example.popularmovies.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Trailer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<Trailer> mTrailers;
    private OnItemClickListener mListener;

    public TrailerAdapter(List<Trailer> mTrailers, OnItemClickListener mListener) {
        this.mTrailers = mTrailers;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        holder.bind(trailer);
        holder.bindListener(trailer, mListener);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        public TextView mTrailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrailerName = itemView.findViewById(R.id.tv_list_item_trailer_name);
        }

        public void bind(final Trailer trailer) {
            mTrailerName.setText(trailer.getName());
        }

        public void bindListener(final Trailer trailer, OnItemClickListener listener) {
            mTrailerName.setOnClickListener(v -> listener.onItemClick(trailer));
        }
    }
}
