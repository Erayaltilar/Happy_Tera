package com.example.android_training.data.remote.dto.movie

data class MovieDto(
    val id: Long,
    val title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_avarage: Float? = null,
    val genres: List<GenresDto>? = null,
)

data class GenresDto(
    val id: Long? = null,
    val name: String? = null,
)