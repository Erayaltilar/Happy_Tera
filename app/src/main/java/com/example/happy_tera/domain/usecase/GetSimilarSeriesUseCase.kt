package com.example.happy_tera.domain.usecase

import com.example.happy_tera.core.Resource
import com.example.happy_tera.domain.model.series_model.SeriesRequest
import com.example.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarSeriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(seriesId: Long): Flow<Resource<SeriesRequest>> {
        return movieRepository.getSimilarSeries(seriesId)
    }
}