package com.lab.fluentbit

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogApi {

    private val logger = LoggerFactory.getLogger(LogApi::class.java)

    @PostMapping("/log")
    fun logging(): String {
        logger.info("Logging is Success!, method = {}", "LogApi.logging()")
        return "Logging is Success!"
    }
}
