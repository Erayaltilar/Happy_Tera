package com.example.android_training.data.repository

import com.example.android_training.core.Resource
import com.example.android_training.data.mapper.toFoodModel
import com.example.android_training.data.remote.FoodApi
import com.example.android_training.domain.model.Food
import com.example.android_training.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodApi: FoodApi,
) : FoodRepository {
    override fun getAllFoods(): Flow<Resource<List<Food>>> = flow {
        try {
            emit(Resource.Loading())
            val foodModels = foodApi.getAllFoods().map { it.toFoodModel() }
            emit(Resource.Success(foodModels))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun getFoodById(id: String): Flow<Resource<Food>> = flow {
        try {
            emit(Resource.Loading())
            foodApi.getFoodByID(id).map { food ->
                val data = food.toFoodModel()
                emit(Resource.Success(data))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }

    override fun searchFoodByKeyword(query: String): Flow<Resource<List<Food>>> = flow {
        try {
            emit(Resource.Loading())
            val data = foodApi.searchFoodByKeyword(query).map { it.toFoodModel() }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }
}