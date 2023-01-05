package com.lab.monitoring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMonitoringApplication

fun main(args: Array<String>) {
    runApplication<KotlinMonitoringApplication>(*args)
}
