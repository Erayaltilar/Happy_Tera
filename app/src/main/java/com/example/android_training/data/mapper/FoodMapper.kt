package com.example.android_training.data.mapper

import com.example.android_training.data.remote.dto.FoodDto
import com.example.android_training.domain.model.Food


fun FoodDto.toFoodModel(): Food {
    return Food(
        id = id,
        description = description,
    )
}