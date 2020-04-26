package com.example.tmdbclientapp.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdbclientapp.R;
import com.example.tmdbclientapp.model.Movie;
import com.example.tmdbclientapp.model.MovieDBResponse;
import com.example.tmdbclientapp.service.GetMovieDataService;
import com.example.tmdbclientapp.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    private GetMovieDataService movieDataService;
    private Application application;

    public MovieDataSource(GetMovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesByPaging(application.getApplicationContext().getString(R.string.api_key),
                1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getResults() != null) {
                    ArrayList<Movie> movies = new ArrayList<>();
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    callback.onResult(movies, null, 2L);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        movieDataService = RetrofitInstance.getService();

        Call<MovieDBResponse> call = movieDataService.getPopularMoviesByPaging(application.getApplicationContext().getString(R.string.api_key),
                params.key);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getResults() != null) {
                    ArrayList<Movie> movies = new ArrayList<>();
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    callback.onResult(movies,params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
