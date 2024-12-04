package com.example.happy_tera.data.remote.dto.series

data class SeriesDto (
    val id: Long,
    val name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_avarage: Float? = null,
    val popularity: Float? = null,
    val genres: List<SeriesGenresDto>? = null,
)

data class SeriesGenresDto(
    val id: Long? = null,
    val name: String? = null,
)