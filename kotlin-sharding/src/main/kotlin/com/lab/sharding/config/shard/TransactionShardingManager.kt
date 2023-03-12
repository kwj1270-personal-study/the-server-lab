package com.lab.sharding.config.shard

import kotlin.concurrent.getOrSet

object TransactionShardingManager {

    private val transactionShardingContext: ThreadLocal<Boolean> = ThreadLocal<Boolean>()

    fun isCurrentTransactionSharding(): Boolean =
        transactionShardingContext.getOrSet { false }

    fun setCurrentTransactionSharding(boolean: Boolean) {
        transactionShardingContext.set(boolean)
    }

    fun refresh() {
        transactionShardingContext.set(false)
    }
}
