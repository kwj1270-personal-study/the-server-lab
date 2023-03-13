package com.lab.queryoffloading

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class KotlinQueryOffloadingApplication

fun main(args: Array<String>) {
    runApplication<KotlinQueryOffloadingApplication>(*args)
}
