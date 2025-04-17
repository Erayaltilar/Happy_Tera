package com.happy_tera.data.remote

import com.happy_tera.data.remote.dto.message.MessageDto
import retrofit2.http.GET

interface MessageApi {
    @GET("advice")
    suspend fun getRandomMessage(): MessageDto
}