package com.example.android_training.domain.usecase

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.Food
import com.example.android_training.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFoodByNameUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
) {
    operator fun invoke(query: String): Flow<Resource<List<Food>>> {
        return foodRepository.searchFoodByKeyword(query)
    }
}