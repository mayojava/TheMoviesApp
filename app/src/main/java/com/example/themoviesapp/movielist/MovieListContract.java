package com.example.themoviesapp.movielist;

import com.example.themoviesapp.basemvp.BasePresenter;
import com.example.themoviesapp.basemvp.BaseView;

import java.util.List;

public interface MovieListContract {
    interface View extends BaseView  {
        void showData(List<MovieUiModel> movieUiModels);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
