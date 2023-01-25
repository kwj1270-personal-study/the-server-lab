package com.lab.elastic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinElasticApplication

fun main(args: Array<String>) {
	runApplication<KotlinElasticApplication>(*args)
}
