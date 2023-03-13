package com.lab.queryoffloading.domain.user

import com.lab.queryoffloading.config.datasource.readwrite.UserReadOnlyDataSourceConfiguration
import com.lab.queryoffloading.domain.user.persistence.User
import com.lab.queryoffloading.domain.user.persistence.ro.UserReadOnlyRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true, transactionManager = UserReadOnlyDataSourceConfiguration.TRANSACTION_MANAGER)
@Service
class UserReadService(
    private val userReadOnlyRepository: UserReadOnlyRepository,
) {
    fun readUser(
        account: String = "",
        password: String = "",
    ): User = userReadOnlyRepository.findByAccountAndPassword(account, password)
        ?: throw EntityNotFoundException("user not found")

    fun count(): Long {
        return userReadOnlyRepository.count()
    }
}
