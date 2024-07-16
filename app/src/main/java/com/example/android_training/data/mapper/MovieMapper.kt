package com.example.android_training.data.mapper

import com.example.android_training.data.remote.dto.movie.MovieDto
import com.example.android_training.data.remote.dto.movie.MovieRequestDto
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.model.movie_model.MovieRequest

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        movie = movie,
        rating = rating,
        image = image,
        imdb_url = imdb_url,
    )
}

fun List<MovieDto>.toMovieList(): List<Movie> {
    return map { it.toMovie() }
}

fun MovieRequestDto.toMovieRequest(): MovieRequest {
    return MovieRequest(
        success = success,
        result = result.toMovieList(),
    )
}