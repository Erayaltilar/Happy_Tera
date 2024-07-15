package com.example.android_training.data.mapper

import com.example.android_training.data.remote.dto.movie.MovieDto
import com.example.android_training.domain.model.movie_model.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        year = year,
        genres = genres,
        image = image,
    )
}