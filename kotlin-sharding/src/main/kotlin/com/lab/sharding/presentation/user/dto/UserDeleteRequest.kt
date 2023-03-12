package com.lab.sharding.presentation.user.dto

data class UserDeleteRequest(
    var account: String = "",
    var password: String = "",
)
