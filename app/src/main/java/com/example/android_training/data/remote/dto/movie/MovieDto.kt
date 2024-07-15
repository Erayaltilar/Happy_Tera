package com.example.android_training.data.remote.dto.movie

data class MovieDto(
    val id: Long? = null,
    val title: String,
    val year: Int,
    val genres: List<String>,
    val image: String,
)
