package com.lab.rabbitmq.service

import com.lab.rabbitmq.controller.request.UserRegistrationRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class FallBackRegistrationService {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["q.fall-back-registration"])
    fun onRegistrationFailure(failedRegistration: UserRegistrationRequest) {
        log.info("실패한 등록 {}에 대한 폴백 실행", failedRegistration)
    }
}
