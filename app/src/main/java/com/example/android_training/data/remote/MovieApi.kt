package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.movie.MovieDto
import retrofit2.http.GET

interface MovieApi {
    @GET("titles/random")
    suspend fun getRandomMovies(): List<MovieDto>
}