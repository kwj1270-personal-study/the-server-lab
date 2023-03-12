package com.lab.sharding.domain.user.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    var account: String = "",
    var password: String = "",
    var nickname: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    fun update(nickname: String): User {
        this.nickname = nickname
        return this
    }
}
