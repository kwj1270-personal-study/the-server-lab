package com.lab.redis.pojo

import java.io.Serializable

data class ChatMessage(
    var sender: String = "",
    var context: String = ""
) : Serializable
