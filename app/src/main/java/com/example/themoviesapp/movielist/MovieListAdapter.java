package com.example.themoviesapp.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviesapp.databinding.MovieRowItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends  RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final List<MovieUiModel> data;
    MovieListAdapter(List<MovieUiModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final MovieRowItemBinding binding = MovieRowItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieRowItemBinding  binding;
        ViewHolder(MovieRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MovieUiModel uiModel) {
            //binding.movieTitle.setText(uiModel.getMovieTitle());
            Picasso.get()
                    .load(uiModel.getImageUrl())
                    .into(binding.moviePoster);
        }
    }
}
