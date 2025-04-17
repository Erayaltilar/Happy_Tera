package com.happy_tera.domain.usecase

import com.happy_tera.core.Resource
import com.happy_tera.domain.model.series_model.SeriesRequest
import com.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchSeriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(query: String): Flow<Resource<SeriesRequest>> {
        return movieRepository.getSearchSeries(query)
    }
}