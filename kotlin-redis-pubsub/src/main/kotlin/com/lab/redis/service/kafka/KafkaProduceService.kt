package com.lab.redis.service.kafka

import com.lab.redis.pojo.ChatMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class KafkaProduceService(
    private val kafkaTemplate: KafkaTemplate<String, ChatMessage>
) {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun pushMessage(topic: String, chatMessage: ChatMessage) {
        val message = MessageBuilder.withPayload(chatMessage)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()

        logger.info("[produce Event] push message. {}", message)
        kafkaTemplate.send(message)
    }
}
