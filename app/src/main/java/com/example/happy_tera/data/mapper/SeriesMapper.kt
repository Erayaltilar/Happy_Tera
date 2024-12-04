package com.example.happy_tera.data.mapper

import com.example.happy_tera.data.remote.dto.series.SeriesDto
import com.example.happy_tera.data.remote.dto.series.SeriesGenresDto
import com.example.happy_tera.data.remote.dto.series.SeriesRequestDto
import com.example.happy_tera.domain.model.series_model.Series
import com.example.happy_tera.domain.model.series_model.SeriesGenres
import com.example.happy_tera.domain.model.series_model.SeriesRequest

fun SeriesDto.toSeries(): Series {
    return Series(
        id = id,
        name = name,
        overview = overview,
        poster_path = poster_path,
        vote_avarage = vote_avarage,
        popularity = popularity,
        genres = genres?.map { it.toSeriesGenreList() }
    )
}

fun List<SeriesDto>.toSeriesList(): List<Series> {
    return map { it.toSeries() }
}

fun SeriesGenresDto.toSeriesGenreList(): SeriesGenres {
    return SeriesGenres(
        id = id,
        name = name,
    )
}

fun SeriesRequestDto.toSeriesRequest(): SeriesRequest {
    return SeriesRequest(
        page = page,
        results = results.toSeriesList(),
    )
}