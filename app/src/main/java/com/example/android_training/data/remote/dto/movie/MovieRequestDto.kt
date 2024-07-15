package com.example.android_training.data.remote.dto.movie

data class MovieRequestDto(
    val success: Boolean,
    val result: List<MovieDto>
)