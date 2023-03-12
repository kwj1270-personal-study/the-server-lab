package com.lab.sharding.config.shard.properties

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.sql.DataSource

@ConfigurationProperties(prefix = "mariadb.routing-datasource")
class ShardingDataSourceProperty(
    private val shards: List<ShardDataSourceFactory>
) {

    fun shardDataSources(): Map<String, DataSource> =
        shards.flatMap { shard ->
            shard.shardDataSources().entries
        }.associate { it.key to it.value }

    class ShardDataSourceFactory(
        private val groupId: Long = 1L,
        private val driverClassName: String = "driverClassName",
        private val username: String = "username",
        private val password: String = "password",
        private val master: ShardDataSourceProperty,
        private val slaves: List<ShardDataSourceProperty>
    ) {

        fun shardDataSources(): Map<String, DataSource> =
            createMasterDataSource() + createSlaveDataSources()

        private fun createMasterDataSource(): Map<String, DataSource> =
            mapOf("${master.name}-${groupId}" to createDataSource(master.url, username, password, driverClassName))

        private fun createSlaveDataSources(): Map<String, DataSource> =
            slaves.associate { createSlaveLookUpKey(it) to createDataSource(it.url, username, password, driverClassName) }

        private fun createSlaveLookUpKey(it: ShardDataSourceProperty): String {
            val slaveLookUpKeyNames = it.name.split("-").toMutableList()
            slaveLookUpKeyNames.add(slaveLookUpKeyNames.size -1, groupId.toString())
            return slaveLookUpKeyNames.joinToString(separator = "-")
        }

        private fun createDataSource(
            url: String = "url",
            username: String = "username",
            password: String = "password",
            driverClassName: String = "driverClassName"
        ): DataSource {
            val dataSource = HikariDataSource()
            dataSource.jdbcUrl = url
            dataSource.username = username
            dataSource.password = password
            dataSource.driverClassName = driverClassName
            return dataSource
        }
    }

    class ShardDataSourceProperty(
        val name: String = "name",
        val url: String = "url"
    )
}
