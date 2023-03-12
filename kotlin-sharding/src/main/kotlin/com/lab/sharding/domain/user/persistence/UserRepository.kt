package com.lab.sharding.domain.user.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByAccountAndPassword(account: String, password: String): User?

    fun deleteByAccountAndPassword(account: String, password: String)
}
