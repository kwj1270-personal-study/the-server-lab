package com.lab.sharding.config.shard.aspect

import com.lab.sharding.config.shard.ShardingTarget

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ShardKey(
    val target: ShardingTarget,
    val key: String,
)
