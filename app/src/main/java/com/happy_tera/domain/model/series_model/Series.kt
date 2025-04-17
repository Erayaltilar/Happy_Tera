package com.happy_tera.domain.model.series_model

data class Series (
    val id: Long,
    val name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_avarage: Float? = null,
    val popularity: Float? = null,
    val genres: List<SeriesGenres>? = null,
)

data class SeriesGenres(
    val id: Long? = null,
    val name: String? = null,
)