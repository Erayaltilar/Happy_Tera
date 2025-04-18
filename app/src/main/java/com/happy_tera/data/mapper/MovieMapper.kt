package com.happy_tera.data.mapper

import com.happy_tera.data.remote.dto.movie.MovieDto
import com.happy_tera.data.remote.dto.movie.MovieGenresDto
import com.happy_tera.data.remote.dto.movie.MovieRequestDto
import com.happy_tera.domain.model.movie_model.Movie
import com.happy_tera.domain.model.movie_model.MovieGenres
import com.happy_tera.domain.model.movie_model.MovieRequest

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        vote_avarage = vote_avarage,
        genres = genres?.map { it.toMovieGenreList() }
    )
}

fun List<MovieDto>.toMovieList(): List<Movie> {
    return map { it.toMovie() }
}

fun MovieGenresDto.toMovieGenreList(): MovieGenres {
    return MovieGenres(
        id = id,
        name = name,
    )
}

fun MovieRequestDto.toMovieRequest(): MovieRequest {
    return MovieRequest(
        page = page,
        results = results.toMovieList(),
    )
}

