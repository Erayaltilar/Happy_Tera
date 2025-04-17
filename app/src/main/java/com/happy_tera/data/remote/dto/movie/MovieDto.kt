package com.happy_tera.data.remote.dto.movie

data class MovieDto(
    val id: Long,
    val title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_avarage: Float? = null,
    val popularity: Float? = null,
    val genres: List<MovieGenresDto>? = null,
)

data class MovieGenresDto(
    val id: Long? = null,
    val name: String? = null,
)