package com.lab.queryoffloading.presentation.user.dto

data class UserReadRequest(
    var account: String = "",
    var password: String = "",
)
