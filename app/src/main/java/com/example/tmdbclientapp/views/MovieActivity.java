package com.example.tmdbclientapp.views;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbclientapp.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdbclientapp.R;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private Intent intent;
    private ImageView imageView;
    private String imagePath;
    private TextView tvMovieTitle, tvMovieRating, tvReleaseDate, tvPlotsynopsis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.ivMovieLarge);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvMovieRating = findViewById(R.id.tvMovieRating);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvPlotsynopsis = findViewById(R.id.tvPlotsynopsis);

        intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            imagePath =  getApplicationContext().getResources().getString(R.string.image_base_url) + movie.getPosterPath();
            Glide.with(this)
                    .load(imagePath)
                    .placeholder(R.drawable.loading)
                    .into(imageView);
            getSupportActionBar().setTitle(movie.getTitle());

            tvMovieTitle.setText(movie.getTitle());
            tvMovieRating.setText(Double.toString(movie.getVoteAverage()));
            tvReleaseDate.setText(movie.getReleaseDate());
            tvPlotsynopsis.setText(movie.getOverview());
        }


    }

}
