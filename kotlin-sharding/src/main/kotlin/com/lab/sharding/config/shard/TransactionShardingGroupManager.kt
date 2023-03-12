package com.lab.sharding.config.shard

import kotlin.concurrent.getOrSet

object TransactionShardingGroupManager {

    private const val DEFAULT_SHARD_KEY = 1

    private val transactionShardingGroupContext: ThreadLocal<Sharding> = ThreadLocal<Sharding>()

    fun getCurrentTransactionShardingGroup(): Sharding =
        transactionShardingGroupContext.getOrSet { Sharding(ShardingTarget.DEFAULT, DEFAULT_SHARD_KEY) }

    fun setCurrentTransactionShardingGroup(target: ShardingTarget, shardKey: Int) {
        transactionShardingGroupContext.set(Sharding(target, shardKey))
    }

    fun refresh() {
        transactionShardingGroupContext.remove()
    }

    class Sharding(
        var target: ShardingTarget,
        var shardKey: Int
    )
}
