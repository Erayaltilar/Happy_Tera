package com.example.happy_tera.data.mapper

import com.example.happy_tera.data.remote.dto.message.AdviceDto
import com.example.happy_tera.data.remote.dto.message.MessageDto
import com.example.happy_tera.domain.model.message_model.Advice
import com.example.happy_tera.domain.model.message_model.Message

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