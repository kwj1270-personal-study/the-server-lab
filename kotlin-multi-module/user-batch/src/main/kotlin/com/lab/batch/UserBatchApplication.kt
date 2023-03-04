package com.lab.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserBatchApplication

fun main(args: Array<String>) {
    runApplication<UserBatchApplication>(*args)
}
