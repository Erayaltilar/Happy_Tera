package com.example.android_training.di

import com.example.android_training.data.remote.FoodApi
import com.example.android_training.data.repository.FoodRepositoryImpl
import com.example.android_training.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFoodRepository(foodApi: FoodApi): FoodRepository {
        return FoodRepositoryImpl(foodApi)
    }
}