package com.example.happy_tera.domain.model.series_model

data class SeriesRequest(
    val page: Int,
    val results: List<Series>
)