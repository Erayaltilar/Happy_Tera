package com.happy_tera.data.repository

import com.happy_tera.core.Resource
import com.happy_tera.data.mapper.toMovie
import com.happy_tera.data.mapper.toMovieRequest
import com.happy_tera.data.mapper.toSeries
import com.happy_tera.data.mapper.toSeriesRequest
import com.happy_tera.data.remote.MovieApi
import com.happy_tera.domain.model.movie_model.Movie
import com.happy_tera.domain.model.movie_model.MovieRequest
import com.happy_tera.domain.model.series_model.Series
import com.happy_tera.domain.model.series_model.SeriesRequest
import com.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieRepository {
    override fun getDiscoverMovie(): Flow<Resource<MovieRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getDiscoverMovie().toMovieRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getMovieDetail(movieId: Long): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getMovieDetail(movieId).toMovie()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getSimilarMovies(movieId: Long): Flow<Resource<MovieRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getSimilarMovies(movieId).toMovieRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getDiscoverSeries(): Flow<Resource<SeriesRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getDiscoverSeries().toSeriesRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getSeriesDetail(seriesId: Long): Flow<Resource<Series>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getSeriesDetail(seriesId).toSeries()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getSimilarSeries(seriesId: Long): Flow<Resource<SeriesRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getSimilarSeries(seriesId).toSeriesRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getSearchMovie(query: String): Flow<Resource<MovieRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getSearchMovies(query).toMovieRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getSearchSeries(query: String): Flow<Resource<SeriesRequest>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getSearchSeries(query).toSeriesRequest()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }
}