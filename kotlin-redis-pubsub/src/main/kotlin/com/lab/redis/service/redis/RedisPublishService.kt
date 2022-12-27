package com.lab.redis.service.redis

import com.lab.redis.pojo.ChatMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisPublishService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun sendMessage(chatMessage: ChatMessage) {
        logger.info("[publish Event] publish message. {}", chatMessage)
        redisTemplate.convertAndSend("chat-message-topic", chatMessage)
    }
}
