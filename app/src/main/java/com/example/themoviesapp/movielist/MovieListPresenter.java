package com.example.themoviesapp.movielist;

import com.example.themoviesapp.model.repository.IMoviesRepository;
import com.example.themoviesapp.model.repository.Mapper;
import com.example.themoviesapp.model.response.PopularMovieResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter implements MovieListContract.Presenter {
    private final IMoviesRepository repository;
    private MovieListContract.View view;
    private Disposable disposable;

    public MovieListPresenter(IMoviesRepository repository, MovieListContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void fetchData() {
        view.showLoading();
        disposable = repository.getPopularMovies(1)
                .map(new Function<PopularMovieResponse, List<MovieUiModel>>() {

                    @Override
                    public List<MovieUiModel> apply(PopularMovieResponse response) {
                        return Mapper.map(response);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run()  {
                        view.hideLoading();
                    }
                })
                .subscribe(new Consumer<List<MovieUiModel>>() {
                    @Override
                    public void accept(List<MovieUiModel> uiModels)  {
                        view.showData(uiModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                    }
                });
    }

    @Override
    public void attachView(MovieListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
