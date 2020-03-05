package com.example.themoviesapp.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.themoviesapp.Injector;
import com.example.themoviesapp.databinding.MovieFragmentBinding;

import java.util.List;

public class MovieListFragment extends Fragment implements MovieListContract.View {
    private MovieFragmentBinding binding;
    private MovieListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MovieFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.movieListRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        presenter = new MovieListPresenter(Injector.getMovieRepo(), this);
        presenter.fetchData();
    }

    public static MovieListFragment getInstance() {
        return new MovieListFragment();
    }

    @Override
    public void showData(List<MovieUiModel> movieUiModel) {
        binding.movieListRecyclerView.setVisibility(View.VISIBLE);
        final MovieListAdapter adapter = new MovieListAdapter(movieUiModel);
        binding.movieListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        binding.loadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
