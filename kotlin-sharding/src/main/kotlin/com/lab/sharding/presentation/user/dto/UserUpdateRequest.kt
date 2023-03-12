package com.lab.sharding.presentation.user.dto

data class UserUpdateRequest(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",
)