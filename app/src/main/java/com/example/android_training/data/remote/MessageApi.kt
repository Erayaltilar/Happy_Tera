package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.MessageDto
import retrofit2.http.GET

interface MessageApi {
    @GET("advice")
    suspend fun getRandomMessage(): MessageDto
}