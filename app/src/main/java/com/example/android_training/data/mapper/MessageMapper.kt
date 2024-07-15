package com.example.android_training.data.mapper

import com.example.android_training.data.remote.dto.message.AdviceDto
import com.example.android_training.data.remote.dto.message.MessageDto
import com.example.android_training.domain.model.message_model.Advice
import com.example.android_training.domain.model.message_model.Message

fun MessageDto.toMessage(): Message {
    return Message(
        slip = slip.toAdvice(),
    )
}

fun AdviceDto.toAdvice(): Advice {
    return Advice(
        id = id,
        advice = advice,
    )
}