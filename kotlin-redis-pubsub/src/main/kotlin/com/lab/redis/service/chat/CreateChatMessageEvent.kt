package com.lab.redis.service.chat

data class CreateChatMessageEvent(
    var sender: String = "",
    var context: String = "",
)
