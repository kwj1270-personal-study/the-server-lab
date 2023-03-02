package com.lab.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserCoreApplication

fun main(args: Array<String>) {
    runApplication<UserCoreApplication>(*args)
}
