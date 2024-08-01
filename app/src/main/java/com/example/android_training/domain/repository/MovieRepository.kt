package com.example.android_training.domain.repository

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.model.movie_model.MovieRequest
import com.example.android_training.domain.model.series_model.Series
import com.example.android_training.domain.model.series_model.SeriesRequest
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