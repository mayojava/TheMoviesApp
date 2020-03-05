package com.example.themoviesapp.movielist;

public class MovieUiModel {
    private String imageUrl;
    private String movieTitle;

    public MovieUiModel(String imageUrl, String movieTitle) {
        this.imageUrl = imageUrl;
        this.movieTitle = movieTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
