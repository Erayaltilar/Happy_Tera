package com.example.android_training.data.repository

import com.example.android_training.core.Resource
import com.example.android_training.data.remote.MovieApi
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieRepository {
    override fun getRandomMovie(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieApi.getRandomMovies()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }
}