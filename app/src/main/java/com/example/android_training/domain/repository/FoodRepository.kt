package com.example.android_training.domain.repository

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllFoods(): Flow<Resource<List<Food>>>
    fun getFoodById(id: String): Flow<Resource<Food>>
    fun searchFoodByKeyword(query: String): Flow<Resource<List<Food>>>
}