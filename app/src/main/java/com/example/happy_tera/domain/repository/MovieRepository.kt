package com.example.happy_tera.domain.repository

import com.example.happy_tera.core.Resource
import com.example.happy_tera.domain.model.movie_model.Movie
import com.example.happy_tera.domain.model.movie_model.MovieRequest
import com.example.happy_tera.domain.model.series_model.Series
import com.example.happy_tera.domain.model.series_model.SeriesRequest
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getDiscoverMovie(): Flow<Resource<MovieRequest>>
    fun getMovieDetail(movieId: Long): Flow<Resource<Movie>>
    fun getSimilarMovies(movieId: Long): Flow<Resource<MovieRequest>>
    fun getDiscoverSeries(): Flow<Resource<SeriesRequest>>
    fun getSeriesDetail(seriesId: Long): Flow<Resource<Series>>
    fun getSimilarSeries(seriesId: Long): Flow<Resource<SeriesRequest>>
    fun getSearchMovie(query: String): Flow<Resource<MovieRequest>>
    fun getSearchSeries(query: String): Flow<Resource<SeriesRequest>>
}