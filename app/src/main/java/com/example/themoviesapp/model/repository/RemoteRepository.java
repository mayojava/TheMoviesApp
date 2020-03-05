package com.example.themoviesapp.model.repository;

import com.example.themoviesapp.Injector;
import com.example.themoviesapp.model.response.PopularMovieResponse;

import io.reactivex.Single;

public class RemoteRepository implements IMoviesRepository {

    @Override
    public Single<PopularMovieResponse> getPopularMovies(int page) {
        return Injector.createMovieListApi().getPopularMovies(page);
    }
}
