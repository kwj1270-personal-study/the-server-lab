package com.lab.monitoring.push

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class KafkaEventPusher(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : EventPusher {
    override fun send(topic: String, message: String) {
        kafkaTemplate.send(
            MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topic).build(),
        )
    }
}
