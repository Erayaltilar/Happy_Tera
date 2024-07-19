package com.example.android_training.domain.usecase

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.movie_model.MovieRequest
import com.example.android_training.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movieId: Long): Flow<Resource<MovieRequest>> {
        return movieRepository.getSimilarMovies(movieId)
    }
}