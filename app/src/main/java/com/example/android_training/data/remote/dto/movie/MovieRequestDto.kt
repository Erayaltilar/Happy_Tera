package com.example.android_training.data.remote.dto.movie

data class MovieRequestDto(
    val page: Int,
    val results: List<MovieDto>
)