package com.example.tmdbclientapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbclientapp.R;
import com.example.tmdbclientapp.databinding.MovieListItemBinding;
import com.example.tmdbclientapp.model.Movie;
import com.example.tmdbclientapp.views.MovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieListItemBinding.setMovie(getItem(position));
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBind) {
            super(movieListItemBind.getRoot());
            this.movieListItemBinding = movieListItemBind;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieActivity.class);
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        intent.putExtra("movie", getItem(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
