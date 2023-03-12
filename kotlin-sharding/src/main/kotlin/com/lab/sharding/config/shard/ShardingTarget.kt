package com.lab.sharding.config.shard

enum class ShardingTarget(
    val target: String,
) {
    DEFAULT("default"),
    BUSINESS("business"),
}
