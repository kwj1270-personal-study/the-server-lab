package com.lab.sharding.presentation.user.dto

data class UserWriteRequest(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",
)