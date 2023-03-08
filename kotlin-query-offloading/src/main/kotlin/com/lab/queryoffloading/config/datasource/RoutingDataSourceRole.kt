package com.lab.queryoffloading.config.datasource

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

enum class RoutingDataSourceRole(
    val isReadOnly: Boolean
) {
    READ_WRITE(false),
    READ_ONLY(true);

    companion object {
        fun of(isReadOnly: Boolean): RoutingDataSourceRole {
            return values().find { it.isReadOnly == isReadOnly }
                ?: throw NotFoundException()
        }
    }
}
