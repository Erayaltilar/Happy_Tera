package com.example.android_training.domain.usecase

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.series_model.SeriesRequest
import com.example.android_training.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiscoverSeriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<Resource<SeriesRequest>> {
        return movieRepository.getDiscoverSeries()
    }
}