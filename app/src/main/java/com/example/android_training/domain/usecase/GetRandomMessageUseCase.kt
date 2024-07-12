package com.example.android_training.domain.usecase

import com.example.android_training.core.Resource
import com.example.android_training.domain.model.Message
import com.example.android_training.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(): Flow<Resource<Message>> {
        return messageRepository.getRandomMessage()
    }
}