package com.lab.queryoffloading.presentation.user.dto

data class UserUpdateRequest(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",
)