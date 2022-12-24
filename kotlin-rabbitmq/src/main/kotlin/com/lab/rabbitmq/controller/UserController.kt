package com.lab.rabbitmq.controller

import com.lab.rabbitmq.controller.request.UserRegistrationRequest
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
class UserController(
    private val rabbitTemplate: RabbitTemplate
) {

    @PostMapping("/user")
    fun convertSendAndReceive(@RequestBody request: UserRegistrationRequest): String {
        rabbitTemplate.convertSendAndReceive("", "q.user-registration", request)
        return "OK"
    }
}