package com.lab.queryoffloading.domain.user

import com.lab.queryoffloading.domain.user.persistence.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserUsecase(
    private val userWriteService: UserWriteService,
    private val userReadService: UserReadService,
) {
    fun readUser(
        account: String = "",
        password: String = "",
    ): User = userReadService.readUser(account, password)

    fun writeUser(
        account: String = "",
        password: String = "",
        nickname: String = "",
    ): Long = userWriteService.writeUser(account, password, nickname)

    fun writeAndCountUser(
        account: String = "",
        password: String = "",
        nickname: String = "",
    ): Long {
        log.info("before user count is = {}", userReadService.count())
        val id = userWriteService.writeUser(account, password, nickname)
        log.info("after user count is = {}", userReadService.count())
        return id
    }

    fun updateUser(
        account: String = "",
        password: String = "",
        nickname: String = "",
    ) {
        val readUser = userReadService.readUser(account, password)
        userWriteService.updateUser(readUser.id!!, readUser.account, readUser.password, nickname)
    }

    fun deleteUser(
        account: String = "",
        password: String = "",
    ) = userWriteService.deleteUser(account, password)

    companion object {
        private val log: Logger = LoggerFactory.getLogger(UserUsecase::class.java)
    }
}
