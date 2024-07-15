package com.example.android_training.domain.model.movie_model

data class MovieRequest(
    val success: Boolean,
    val result: List<Movie>
)