package com.lab.sharding.presentation.board.dto

data class BoardWriteRequest(
    var account: String = "",
    var password: String = "",
    var title: String = "",
    var content: String = "",
)
