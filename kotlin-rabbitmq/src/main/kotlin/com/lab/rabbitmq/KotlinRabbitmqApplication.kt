package com.lab.rabbitmq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRabbitmqApplication

fun main(args: Array<String>) {
    runApplication<KotlinRabbitmqApplication>(*args)
}
