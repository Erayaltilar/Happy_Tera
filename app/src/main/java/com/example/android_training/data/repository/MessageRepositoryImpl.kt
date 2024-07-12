package com.example.android_training.data.repository

import com.example.android_training.core.Resource
import com.example.android_training.data.mapper.toMessage
import com.example.android_training.data.remote.MessageApi
import com.example.android_training.domain.model.Message
import com.example.android_training.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageApi: MessageApi,
) : MessageRepository {
    override fun getRandomMessage(): Flow<Resource<Message>> = flow {
        try {
            emit(Resource.Loading())
            val message = messageApi.getRandomMessage().toMessage()
            emit(Resource.Success(message))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.orEmpty()))
        }
    }
}