package com.example.happy_tera.domain.usecase

import com.example.happy_tera.core.Resource
import com.example.happy_tera.domain.model.movie_model.MovieRequest
import com.example.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSearchMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(query: String): Flow<Resource<MovieRequest>> {
        return movieRepository.getSearchMovie(query)
    }
}