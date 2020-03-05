package com.example.themoviesapp.model.repository;

import com.example.themoviesapp.model.response.PopularMovieResponse;

import io.reactivex.Single;

public interface IMoviesRepository {
    Single<PopularMovieResponse> getPopularMovies(int page);
}
