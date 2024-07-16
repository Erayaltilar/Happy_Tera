package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.movie.MovieDto
import com.example.android_training.data.remote.dto.movie.MovieRequestDto
import com.example.android_training.domain.model.movie_model.Movie
import retrofit2.http.GET

interface MovieApi {
    @GET("api/movies")
    suspend fun getRandomMovies(): List<Movie>
}