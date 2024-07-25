package com.example.android_training.domain.usecase

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.series_model.Series
import com.example.android_training.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeriesDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(seriesId: Long): Flow<Resource<Series>> {
        return movieRepository.getSeriesDetail(seriesId = seriesId)
    }
}