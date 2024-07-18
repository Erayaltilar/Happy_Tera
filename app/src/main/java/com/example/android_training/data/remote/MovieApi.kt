package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.movie.MovieDto
import com.example.android_training.data.remote.dto.movie.MovieRequestDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(): MovieRequestDto

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): MovieDto
}