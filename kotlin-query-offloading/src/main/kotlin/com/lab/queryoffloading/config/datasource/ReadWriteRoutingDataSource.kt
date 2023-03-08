package com.lab.queryoffloading.config.datasource

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@ConfigurationProperties(prefix = "mariadb.routing-datasource.read-write")
data class ReadWriteRoutingDataSource(
    val url: String = "url",
    val driverClassName: String = "driverClassName",
    val username: String = "username",
    val password: String = "passwd",
): CustomRoutingDataSource {

    override fun isReadOnly(): Boolean = false

    override fun toRouterDatasource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        dataSource.setDriverClassName(driverClassName)
        return dataSource
    }
}
