package com.example.android_training.domain.model.movie_model

data class MovieRequest(
    val page: Int,
    val results: List<Movie>
)