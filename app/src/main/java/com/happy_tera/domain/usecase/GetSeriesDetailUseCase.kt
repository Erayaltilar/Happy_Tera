package com.happy_tera.domain.usecase

import com.happy_tera.core.Resource
import com.happy_tera.domain.model.series_model.Series
import com.happy_tera.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeriesDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(seriesId: Long): Flow<Resource<Series>> {
        return movieRepository.getSeriesDetail(seriesId = seriesId)
    }
}