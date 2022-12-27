package com.lab.redis.api.request

import com.lab.redis.pojo.ChatMessage

data class CreateChatMessageRequest(
    var sender: String = "",
    var context: String = "",
) {
    fun toChatMessage(): ChatMessage {
        return ChatMessage(sender, context)
    }
}
