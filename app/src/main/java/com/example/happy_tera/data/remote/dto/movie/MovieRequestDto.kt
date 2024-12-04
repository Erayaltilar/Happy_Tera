package com.example.happy_tera.data.remote.dto.movie

data class MovieRequestDto(
    val page: Int,
    val results: List<MovieDto>
)