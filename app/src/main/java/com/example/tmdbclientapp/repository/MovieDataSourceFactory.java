package com.example.tmdbclientapp.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdbclientapp.service.GetMovieDataService;
import com.example.tmdbclientapp.service.RetrofitInstance;

public class MovieDataSourceFactory extends DataSource.Factory {
    private GetMovieDataService movieDataService;
    private Application application;
    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(GetMovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataService = RetrofitInstance.getService();
        movieDataSource = new MovieDataSource(movieDataService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
