package com.example.android_training.data.remote

import com.example.android_training.data.remote.dto.FoodDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("getFoodByID")
    suspend fun getFoodByID(
        @Query("foodID") foodID: String
    ): List<FoodDto>

    @GET("getAllFoods")
    suspend fun getAllFoods(): List<FoodDto>

    @GET("searchByKeyword")
    suspend fun searchFoodByKeyword(
        @Query("keyword") keyword: String
    ): List<FoodDto>
}