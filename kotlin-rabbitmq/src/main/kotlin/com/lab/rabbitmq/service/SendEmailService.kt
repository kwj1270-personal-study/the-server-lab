package com.lab.rabbitmq.service

import com.lab.rabbitmq.controller.request.UserRegistrationRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class SendEmailService {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["q.send-email"])
    fun sendEmail(request: UserRegistrationRequest) {
        log.info("{}에 이메일 보내기", request.email)
    }
}
