package com.example.android_training.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object AdviceHomepageScreen : Screens()

    @Serializable
    data object HomepageScreen : Screens()

    @Serializable
    data class MovieDetailScreen(
        val movieId: Long,
    ) : Screens()
    @Serializable
    data class SeriesDetailScreen(
        val seriesId: Long,
    ) : Screens()

    @Serializable
    data class SimilarMoviesScreen(
        val movieId: Long,
    ) : Screens()
    @Serializable
    data class SimilarSeriesScreen(
        val seriesId: Long,
    ) : Screens()

}