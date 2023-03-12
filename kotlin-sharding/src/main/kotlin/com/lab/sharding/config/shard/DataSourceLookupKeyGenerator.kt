package com.lab.sharding.config.shard

import com.lab.sharding.strategy.LookUpKeyGeneratorStrategy
import com.lab.sharding.strategy.MasterLookUpKeyGeneratorStrategy
import com.lab.sharding.strategy.SlaveLookUpKeyGeneratorStrategy

enum class DataSourceLookupKeyGenerator(
    private val role: String,
    private val lookUpKeyStrategy : LookUpKeyGeneratorStrategy
) {
    MASTER("master", MasterLookUpKeyGeneratorStrategy()),
    SLAVE("slave", SlaveLookUpKeyGeneratorStrategy()),
    ;

    fun determineCurrentLookupKey(sharding: TransactionShardingGroupManager.Sharding): String =
        lookUpKeyStrategy.createLookUpKey(role, sharding)
}
