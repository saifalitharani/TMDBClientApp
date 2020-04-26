package com.example.tmdbclientapp.views;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbclientapp.databinding.ActivityMovieBinding;
import com.example.tmdbclientapp.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdbclientapp.R;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Toolbar toolbar = activityMovieBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }

}
