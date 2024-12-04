package com.example.happy_tera.domain.usecase

import com.example.happy_tera.core.Resource
import com.example.happy_tera.domain.model.message_model.Message
import com.example.happy_tera.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(): Flow<Resource<Message>> {
        return messageRepository.getRandomMessage()
    }
}