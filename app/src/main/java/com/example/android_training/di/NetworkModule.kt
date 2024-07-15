package com.example.android_training.di

import com.example.android_training.core.Constants.BASE_URL_ADVICE_API
import com.example.android_training.core.Constants.BASE_URL_MOVIE_API
import com.example.android_training.data.remote.MessageApi
import com.example.android_training.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMessageApi(retrofit: Retrofit): MessageApi {
        return retrofit.create(MessageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIE_API)
            .baseUrl(BASE_URL_ADVICE_API)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "apikey 7eKwtU0FVNCtLI1GJB6veH:59CgYgWd3iufP7nVNvNKiO")
                    .build()
                chain.proceed(request)
            }
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
}