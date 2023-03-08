package com.lab.queryoffloading.presentation.user.dto

data class UserWriteRequest(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",
)