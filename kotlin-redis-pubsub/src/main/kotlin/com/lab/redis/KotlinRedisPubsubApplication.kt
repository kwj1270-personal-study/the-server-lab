package com.lab.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRedisPubsubApplication

fun main(args: Array<String>) {
    runApplication<KotlinRedisPubsubApplication>(*args)
}
