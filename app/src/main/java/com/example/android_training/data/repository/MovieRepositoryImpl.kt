package com.example.android_training.data.repository

import com.example.android_training.core.Resource
import com.example.android_training.data.mapper.toMovie
import com.example.android_training.data.mapper.toMovieRequest
import com.example.android_training.data.remote.MovieApi
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.model.movie_model.MovieRequest
import com.example.android_training.domain.repository.MovieRepository
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
}