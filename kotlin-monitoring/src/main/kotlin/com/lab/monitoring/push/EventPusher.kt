package com.lab.monitoring.push

interface EventPusher {
    fun send(topic: String, message: String)
}
