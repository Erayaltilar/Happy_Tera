package com.happy_tera.di

import com.happy_tera.data.remote.MessageApi
import com.happy_tera.data.remote.MovieApi
import com.happy_tera.data.repository.MessageRepositoryImpl
import com.happy_tera.data.repository.MovieRepositoryImpl
import com.happy_tera.domain.repository.MessageRepository
import com.happy_tera.domain.repository.MovieRepository
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