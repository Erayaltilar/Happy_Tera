package com.example.android_training.domain.model.movie_model

data class Movie(
    val id: Long? = null,
    val title: String,
    val year: Int,
    val genres: List<String>,
    val image: String,
)
