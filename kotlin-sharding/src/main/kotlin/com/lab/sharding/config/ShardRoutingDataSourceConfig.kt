package com.lab.sharding.config

import com.lab.sharding.config.shard.ShardingDatasourceRouter
import com.lab.sharding.config.shard.properties.ShardingDataSourceProperty
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
class ShardRoutingDataSourceConfig(
    private val shardingDataSourceProperty: ShardingDataSourceProperty
) {

    @Primary
    @Bean
    fun datasource(@Qualifier("shardingDatasource") datasource: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(datasource)
    }

    @Bean
    fun shardingDatasource(): DataSource {
        return ShardingDatasourceRouter(shardingDataSourceProperty.shardDataSources())
    }
}
