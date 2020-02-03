package com.example.themoviedbpopularmovies.network;

import com.example.themoviedbpopularmovies.Constants;
import com.example.themoviedbpopularmovies.model.MovieDbDetailModel;
import com.example.themoviedbpopularmovies.model.MovieDbModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Client {
    @GET("movie/popular")
    Call<MovieDbModel> getPopularMovies(@Query(Constants.API_KEY_NAME)String spi);

    @GET("movie/{movie_id}")
    Call<MovieDbDetailModel> getMovieDetails(@Path("movie_id")String id, @Query(Constants.API_KEY_NAME)String spi);


}
