package com.lab.queryoffloading.presentation.user.dto

data class UserDeleteRequest(
    var account: String = "",
    var password: String = "",
)
