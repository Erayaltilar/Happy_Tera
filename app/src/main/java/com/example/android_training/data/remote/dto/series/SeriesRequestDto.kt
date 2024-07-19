package com.example.android_training.data.remote.dto.series

data class SeriesRequestDto(
    val page: Int,
    val results: List<SeriesDto>
)