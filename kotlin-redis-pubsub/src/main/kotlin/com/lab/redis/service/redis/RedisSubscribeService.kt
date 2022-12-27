package com.lab.redis.service.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.lab.redis.pojo.ChatMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class RedisSubscribeService : MessageListener {

    private val mapper: ObjectMapper = ObjectMapper()
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val chatMessage: ChatMessage = mapper.readValue(message.body, ChatMessage::class.java)
        logger.info("[subscribe Event] got message. {}", chatMessage)
    }
}
