package com.happy_tera.domain.repository

import com.happy_tera.core.Resource
import com.happy_tera.domain.model.message_model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getRandomMessage(): Flow<Resource<Message>>
}