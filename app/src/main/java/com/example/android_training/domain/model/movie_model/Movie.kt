package com.example.android_training.domain.model.movie_model

data class Movie(
    val id: Long,
    val title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_avarage: Float? = null,
    val genres: List<MovieGenres>? = null,
)

data class MovieGenres(
    val id: Long? = null,
    val name: String? = null,
)
