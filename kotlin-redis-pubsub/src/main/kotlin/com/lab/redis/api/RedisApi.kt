package com.lab.redis.api

import com.lab.redis.pojo.ChatMessage
import com.lab.redis.service.kafka.KafkaProduceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisApi(
    private val kafkaProduceService: KafkaProduceService
) {

    @PostMapping("/api/chat")
    fun pubSub(@RequestBody chatMessage: ChatMessage): String {
        kafkaProduceService.pushMessage("chat-message-topic", chatMessage)
        return "success"
    }
}
