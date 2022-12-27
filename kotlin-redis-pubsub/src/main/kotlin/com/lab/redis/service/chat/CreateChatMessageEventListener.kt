package com.lab.redis.service.chat

import com.lab.redis.pojo.ChatMessage
import com.lab.redis.service.redis.RedisPublishService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CreateChatMessageEventListener(
    private val redisPubService: RedisPublishService
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @EventListener
    fun publishChatMessage(createChatMessageEvent: CreateChatMessageEvent) {
        logger.info("[send Event] send to redis pubsub = {}", createChatMessageEvent)
        redisPubService.sendMessage(ChatMessage(createChatMessageEvent.sender, createChatMessageEvent.context))
    }
}
