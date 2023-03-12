package com.lab.sharding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinShardingApplication

fun main(args: Array<String>) {
    runApplication<KotlinShardingApplication>(*args)
}
