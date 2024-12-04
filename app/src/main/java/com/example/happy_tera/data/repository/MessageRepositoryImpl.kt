package com.example.happy_tera.data.repository

import com.example.happy_tera.core.Resource
import com.example.happy_tera.data.mapper.toMessage
import com.example.happy_tera.data.remote.MessageApi
import com.example.happy_tera.domain.model.message_model.Message
import com.example.happy_tera.domain.repository.MessageRepository
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