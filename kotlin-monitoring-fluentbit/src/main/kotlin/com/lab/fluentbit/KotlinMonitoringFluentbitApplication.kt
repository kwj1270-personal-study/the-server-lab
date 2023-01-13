package com.lab.fluentbit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMonitoringFluentbitApplication

fun main(args: Array<String>) {
    runApplication<KotlinMonitoringFluentbitApplication>(*args)
}
