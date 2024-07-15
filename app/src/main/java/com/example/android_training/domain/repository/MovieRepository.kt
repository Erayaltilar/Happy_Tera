package com.example.android_training.domain.repository

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.model.movie_model.MovieRequest
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getRandomMovie(): Flow<Resource<MovieRequest>>
}