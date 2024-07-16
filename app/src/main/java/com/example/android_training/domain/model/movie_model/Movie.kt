package com.example.android_training.domain.model.movie_model

data class Movie(
    val id: Long,
    val movie: String,
    val rating: String,
    val image: String,
    val imdb_url: String,
)
