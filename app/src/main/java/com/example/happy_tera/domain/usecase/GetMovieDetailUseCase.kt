package com.example.happy_tera.domain.usecase

import com.example.happy_tera.core.Resource
import com.example.happy_tera.domain.model.movie_model.Movie
import com.example.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movieId : Long): Flow<Resource<Movie>> {
        return movieRepository.getMovieDetail(movieId)
    }
}