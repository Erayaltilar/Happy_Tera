package com.example.happy_tera.data.remote.dto.series

data class SeriesRequestDto(
    val page: Int,
    val results: List<SeriesDto>
)