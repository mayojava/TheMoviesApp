package com.example.themoviesapp.model.repository;

import com.example.themoviesapp.model.response.PopularMovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListApi {
    @GET("movie/popular")
    Single<PopularMovieResponse> getPopularMovies(@Query("page") int page);
}
