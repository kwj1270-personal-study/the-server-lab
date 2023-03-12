package com.lab.sharding.strategy

interface ShardingTypeStrategy {
    fun createKey(): String
}

enum class ShardingStrategy(
    private val shardingTypeStrategy: ShardingTypeStrategy
) {
    RANGE(RangeShardingTypeStrategy()),
    MODULAR(ModularShardingTypeStrategy());

    fun isRange(): Boolean = this == RANGE
    fun isModule(): Boolean = this == MODULAR

    fun createKey(): String =
        shardingTypeStrategy.createKey()
}

class RangeShardingTypeStrategy: ShardingTypeStrategy {
    override fun createKey(): String {
        TODO("Not yet implemented")
    }
}

class ModularShardingTypeStrategy: ShardingTypeStrategy {
    override fun createKey(): String {
        TODO("Not yet implemented")
    }
}
