package com.example.android_training.data.mapper

import com.example.android_training.data.remote.dto.AdviceDto
import com.example.android_training.data.remote.dto.MessageDto
import com.example.android_training.domain.model.Advice
import com.example.android_training.domain.model.Message

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