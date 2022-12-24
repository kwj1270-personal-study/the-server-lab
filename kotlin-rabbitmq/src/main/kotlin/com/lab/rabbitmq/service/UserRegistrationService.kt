package com.lab.rabbitmq.service

import com.lab.rabbitmq.controller.request.UserRegistrationRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class UserRegistrationService(
    private val rabbitTemplate: RabbitTemplate,
) {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["q.user-registration"], containerFactory = "registrationListenerContainerFactory")
    fun onUserRegistration(event: UserRegistrationRequest) {
        log.info("사용자 등록 이벤트 수신: {}", event)

        executeRegistration(event)

        rabbitTemplate.convertAndSend("x.post-registration", "", event)
    }

    private fun executeRegistration(event: UserRegistrationRequest) {
        log.info("사용자 등록 이벤트 실행: {}", event)
        throw RuntimeException("등록 실패")
    }
}
