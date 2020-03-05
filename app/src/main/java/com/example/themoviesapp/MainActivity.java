package com.example.themoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.themoviesapp.movielist.MovieListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MovieListFragment.getInstance())
                    .commit();
        }
    }
}
