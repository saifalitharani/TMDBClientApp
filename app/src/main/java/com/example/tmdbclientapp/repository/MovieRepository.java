package com.example.tmdbclientapp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.tmdbclientapp.R;
import com.example.tmdbclientapp.model.Movie;
import com.example.tmdbclientapp.model.MovieDBResponse;
import com.example.tmdbclientapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> movieMutableLiveData = new MutableLiveData<>();
    private Application application;
    private MovieDBResponse movieDBResponse;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        final Call<MovieDBResponse> movieDBResponseCall = RetrofitInstance.getService().getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        movieDBResponseCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getResults() != null && movieDBResponse.getResults().size() > 0) {
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    movieMutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return movieMutableLiveData;
    }
}