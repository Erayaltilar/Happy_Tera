package com.happy_tera.domain.model.movie_model

data class MovieRequest(
    val page: Int,
    val results: List<Movie>
)