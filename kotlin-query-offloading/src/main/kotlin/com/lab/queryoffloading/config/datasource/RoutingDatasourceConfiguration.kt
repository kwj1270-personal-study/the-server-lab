package com.lab.queryoffloading.config.datasource

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
class RoutingDatasourceConfiguration(
) {

    @Bean(name = ["masterDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.master")
    fun masterDataSource(): DataSource = DataSourceBuilder.create()
        .type(HikariDataSource::class.java)
        .build()

    @Bean(name = ["slaveDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    fun slaveDataSource(): DataSource = DataSourceBuilder.create()
        .type(HikariDataSource::class.java)
        .build()
        .apply {
            isReadOnly = true
        }

    @Primary
    @ConditionalOnBean(name = ["datasourceRouter"])
    @Bean(name = ["datasource"])
    fun datasource(@Qualifier("datasourceRouter") datasourceRouter: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(datasourceRouter)
    }

    @Bean(name = ["datasourceRouter"])
    @ConditionalOnBean(name = ["masterDataSource", "slaveDataSource"])
    fun datasourceRouter(
        @Qualifier("slaveDataSource") slaveDataSource: DataSource,
        @Qualifier("masterDataSource") masterDataSource: DataSource,
    ): DataSource {
        val dataSourcesMap: Map<String, DataSource> = mapOf("master" to masterDataSource, "slave" to slaveDataSource)
        return DataSourceRouter(dataSourcesMap, masterDataSource)
    }
}
