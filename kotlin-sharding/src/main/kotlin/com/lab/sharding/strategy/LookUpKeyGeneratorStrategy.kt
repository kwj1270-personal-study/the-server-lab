package com.lab.sharding.strategy

import com.lab.sharding.config.shard.TransactionShardingGroupManager

interface LookUpKeyGeneratorStrategy {
    fun createLookUpKey(role: String, sharding: TransactionShardingGroupManager.Sharding): String
}

class MasterLookUpKeyGeneratorStrategy : LookUpKeyGeneratorStrategy {
    override fun createLookUpKey(role: String, sharding: TransactionShardingGroupManager.Sharding): String =
        "${sharding.target.target}-${role}-${sharding.shardKey}"
}

class SlaveLookUpKeyGeneratorStrategy : LookUpKeyGeneratorStrategy {
    override fun createLookUpKey(role: String, sharding: TransactionShardingGroupManager.Sharding) =
        "${sharding.target.target}-${role}-${sharding.shardKey}-1"
}
