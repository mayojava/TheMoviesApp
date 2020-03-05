package com.example.themoviesapp.basemvp;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
