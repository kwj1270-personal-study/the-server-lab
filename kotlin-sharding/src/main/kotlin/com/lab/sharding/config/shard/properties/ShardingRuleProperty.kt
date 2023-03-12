package com.lab.sharding.config.shard.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mariadb.routing-datasource.sharding")
class ShardingRuleProperty(
    private val rules: List<ShardRule>
) {

    fun calculateShardKey(condition: String): Int =
        rules.filter { it.isBelong(condition.toLong()) }
            .map { it.shardNo }
            .first()

    class ShardRule(
        val shardNo: Int = 1,
        private val rangeMin: Long = 0,
        private val rangeMax: Long = 0
    ) {
        fun isBelong(condition: Long): Boolean =
            condition in rangeMin..rangeMax
    }
}
