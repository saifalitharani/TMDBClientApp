package com.example.tmdbclientapp.service;

import com.example.tmdbclientapp.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieDataService {


    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesByPaging(@Query("api_key") String apiKey, @Query("page") long page);

}
