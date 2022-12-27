package com.lab.redis.service.kafka

import com.lab.redis.pojo.ChatMessage
import com.lab.redis.service.chat.CreateChatMessageEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class KafkaConsumeService(
    private val publisher: ApplicationEventPublisher
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"], containerFactory = "pushConsumer")
    fun listenEvent(request: ChatMessage) {
        logger.info("[consumeEvent] got message. {}", request)
        publisher.publishEvent(CreateChatMessageEvent(request.sender, request.context))
    }
}
