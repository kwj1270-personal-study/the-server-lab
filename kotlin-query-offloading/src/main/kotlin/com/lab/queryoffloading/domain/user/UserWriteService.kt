package com.lab.queryoffloading.domain.user

import com.lab.queryoffloading.config.datasource.readwrite.UserReadOnlyDataSourceConfiguration
import com.lab.queryoffloading.config.datasource.readwrite.UserReadWriteDataSourceConfiguration
import com.lab.queryoffloading.domain.user.persistence.User
import com.lab.queryoffloading.domain.user.persistence.ro.UserReadOnlyRepository
import com.lab.queryoffloading.domain.user.persistence.rw.UserReadWriteRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, transactionManager = UserReadWriteDataSourceConfiguration.TRANSACTION_MANAGER)
@Service
class UserWriteService(
    private val userReadWriteRepository: UserReadWriteRepository,
) {

    fun writeUser(
        account: String = "",
        password: String = "",
        nickname: String = "",
    ): Long =
        userReadWriteRepository.saveAndFlush(
            User(account = account, password = password, nickname = nickname)
        ).id!!

    fun updateUser(
        id: Long = 0,
        account: String = "",
        password: String = "",
        nickname: String = "",
    ) = userReadWriteRepository.saveAndFlush(User(account, password, nickname, id))

    fun deleteUser(
        account: String = "",
        password: String = "",
    ) = userReadWriteRepository.deleteByAccountAndPassword(account, password)
}
