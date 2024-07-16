package com.example.android_training.data.remote.dto.movie

data class MovieDto(
    val id: Long,
    val movie: String,
    val rating: String,
    val image: String,
    val imdb_url: String,
)
