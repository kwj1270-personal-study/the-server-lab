package com.lab.monitoring.presentation

import com.lab.monitoring.push.KafkaEventPusher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogApi(
    private val kafkaEventPusher: KafkaEventPusher
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/log")
    fun logging(): String {
        logger.info("Logging is Success!, method = {}", "LogApi.logging()")
        kafkaEventPusher.send("app-log", "Logging is Success!, method = LogApi.logging()")
        return "Logging is Success!"
    }
}
