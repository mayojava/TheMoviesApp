package com.example.themoviesapp.model.repository;

import com.example.themoviesapp.model.response.PopularMovieResponse;
import com.example.themoviesapp.model.response.Result;
import com.example.themoviesapp.movielist.MovieUiModel;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static final String BASE = "https://image.tmdb.org/t/p/w500/";

    public static List<MovieUiModel> map(PopularMovieResponse response) {
        final List<MovieUiModel> result = new ArrayList<>(response.getResults().size());

        for (Result res: response.getResults()) {
            result.add(new MovieUiModel(createImageUrl(res.getPosterPath()), res.getTitle()));
        }

        return result;
    }

    private static String createImageUrl(String path) {
        return String.format("%s%s", BASE, path);
    }
}
