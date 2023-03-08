package com.lab.queryoffloading.presentation.user.dto

import com.lab.queryoffloading.domain.user.persistence.User

data class UserReadResponse(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",
    var id: Long = 0L
) {
    constructor(user: User): this(user.account, user.password, user.nickname, user.id!!)
}
