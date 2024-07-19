package com.example.android_training.domain.model.series_model

data class SeriesRequest(
    val page: Int,
    val results: List<Series>
)