package com.example.themoviesapp;

import com.example.themoviesapp.model.repository.IMoviesRepository;
import com.example.themoviesapp.model.repository.MovieListApi;
import com.example.themoviesapp.model.repository.RemoteRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    private static  MovieListApi api;

    public static MovieListApi createMovieListApi() {
        if (api == null) {
            api = retrofit().create(MovieListApi.class);
        }
        return api;
    }

    public static IMoviesRepository getMovieRepo() {
        return new RemoteRepository();
    }

    private static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new  OkHttpClient.Builder()
                .addInterceptor(movieApiInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private static Interceptor movieApiInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request();
                final HttpUrl originalUrl = request.url();

                final HttpUrl updatedurl = originalUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .addQueryParameter("language", "en-US")
                        .build();

                final Request updatedRequest = request.newBuilder()
                        .url(updatedurl).build();

                return chain.proceed(updatedRequest);
            }
        };
    }
}
