package com.example.android_training.di

import com.example.android_training.data.remote.MessageApi
import com.example.android_training.data.remote.MovieApi
import com.example.android_training.data.repository.MessageRepositoryImpl
import com.example.android_training.data.repository.MovieRepositoryImpl
import com.example.android_training.domain.repository.MessageRepository
import com.example.android_training.domain.repository.MovieRepository
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
    fun provideMessageRepository(messageApi: MessageApi): MessageRepository {
        return MessageRepositoryImpl(messageApi)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}