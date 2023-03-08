package com.lab.queryoffloading.domain.user

import com.lab.queryoffloading.domain.user.persistence.User
import com.lab.queryoffloading.domain.user.persistence.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false)
@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun readUser(
        account: String = "",
        password: String = ""
    ): User = userRepository.findByAccountAndPassword(account, password)
        ?: throw EntityNotFoundException("user not found")

    fun writeUser(
        account: String = "",
        password: String = "",
        nickname: String = "",
    ): Long =
        userRepository.saveAndFlush(
            User(account = account, password = password, nickname = nickname)
        ).id!!

    fun updateUser(
        account: String = "",
        password: String = "",
        nickname: String = ""
    ) = userRepository.saveAndFlush(
        userRepository.findByAccountAndPassword(account, password)
            ?: throw EntityNotFoundException("user not found")
        )

    fun deleteUser(
        account: String = "",
        password: String = "",
    ) = userRepository.deleteByAccountAndPassword(account, password)
}
