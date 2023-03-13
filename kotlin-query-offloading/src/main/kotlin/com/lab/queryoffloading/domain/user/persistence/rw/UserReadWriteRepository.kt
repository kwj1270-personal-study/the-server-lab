package com.lab.queryoffloading.domain.user.persistence.rw

import com.lab.queryoffloading.domain.user.persistence.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserReadWriteRepository : JpaRepository<User, Long> {
    fun deleteByAccountAndPassword(account: String, password: String)
}
