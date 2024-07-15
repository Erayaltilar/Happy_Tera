package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.movie.MovieRequestDto
import retrofit2.http.GET

interface MovieApi {
    @GET("watching/imdb")
    suspend fun getRandomMovies(): MovieRequestDto
}