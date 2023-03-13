package com.lab.queryoffloading.domain.user.persistence.ro

import com.lab.queryoffloading.domain.user.persistence.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserReadOnlyRepository : JpaRepository<User, Long> {
    fun findByAccountAndPassword(account: String, password: String): User?
}
