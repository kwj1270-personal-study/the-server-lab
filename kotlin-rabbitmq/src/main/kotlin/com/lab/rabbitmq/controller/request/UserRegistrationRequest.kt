package com.lab.rabbitmq.controller.request

import java.io.Serializable

data class UserRegistrationRequest(
    var username: String = "",
    var email: String = "",
    var mobileNumber: String = "",
): Serializable
