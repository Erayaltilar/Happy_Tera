package com.example.android_training.domain.repository

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getRandomMessage(): Flow<Resource<Message>>
}