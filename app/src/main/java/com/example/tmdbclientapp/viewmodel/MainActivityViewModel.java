package com.example.tmdbclientapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdbclientapp.model.Movie;
import com.example.tmdbclientapp.repository.MovieDataSource;
import com.example.tmdbclientapp.repository.MovieDataSourceFactory;
import com.example.tmdbclientapp.repository.MovieRepository;
import com.example.tmdbclientapp.service.GetMovieDataService;
import com.example.tmdbclientapp.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private MovieDataSourceFactory movieDataSourceFactory;
    private GetMovieDataService movieDataService;
    private MutableLiveData<MovieDataSource> mutableLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieDataService = RetrofitInstance.getService();
        movieDataSourceFactory = new MovieDataSourceFactory(movieDataService, application);
        //movieRepository = new MovieRepository(application);
        mutableLiveData = movieDataSourceFactory.getMutableLiveData();

        //Defining the PagedList configuration:
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();


        executor = Executors.newFixedThreadPool(5);
        pagedListMutableLiveData = (new LivePagedListBuilder<Long, Movie>(movieDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getPagedListMutableLiveData() {
        return pagedListMutableLiveData;
    }
}
