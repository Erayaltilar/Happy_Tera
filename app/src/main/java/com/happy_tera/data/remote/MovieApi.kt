package com.happy_tera.data.remote

import com.happy_tera.data.remote.dto.movie.MovieDto
import com.happy_tera.data.remote.dto.movie.MovieRequestDto
import com.happy_tera.data.remote.dto.series.SeriesDto
import com.happy_tera.data.remote.dto.series.SeriesRequestDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(): MovieRequestDto

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): MovieDto

    @GET("3/movie/{movie_Id}/similar")
    suspend fun getSimilarMovies(@Path("movie_Id") movieId: Long): MovieRequestDto

    @GET("3/discover/tv")
    suspend fun getDiscoverSeries(): SeriesRequestDto

    @GET("3/tv/{series_id}")
    suspend fun getSeriesDetail(@Path("series_id") seriesId: Long): SeriesDto

    @GET("3/tv/{series_id}/similar")
    suspend fun getSimilarSeries(@Path("series_id") seriesId: Long): SeriesRequestDto

    @GET("3/search/movie")
    suspend fun getSearchMovies(@Query("q") query: String) : MovieRequestDto
    @GET("3/search/tv")
    suspend fun getSearchSeries(@Query("q") query: String) : SeriesRequestDto
}