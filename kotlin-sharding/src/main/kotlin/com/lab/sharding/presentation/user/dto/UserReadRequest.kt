package com.lab.sharding.presentation.user.dto

data class UserReadRequest(
    var account: String = "",
    var password: String = "",
)
