package com.lab.queryoffloading.config.datasource

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
class RoutingDatasourceConfiguration(
    private val customRoutingDataSources: List<CustomRoutingDataSource>
) {

    @Bean
    @Primary
    fun datasource(@Qualifier("customRoutingDatasource") dataSource: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(dataSource)
    }

    @Bean
    fun customRoutingDatasource(): DataSource {
        val routingDataSourceMap =
            customRoutingDataSources.associate { RoutingDataSourceRole.of(it.isReadOnly()) to it.toRouterDatasource() }
        return DataSourceRouter(
            routingDataSourceMap,
            routingDataSourceMap[RoutingDataSourceRole.READ_WRITE]!!
        )
    }
}
