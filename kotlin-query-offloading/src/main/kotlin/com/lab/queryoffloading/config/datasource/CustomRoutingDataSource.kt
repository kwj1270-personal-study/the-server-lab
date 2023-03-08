package com.lab.queryoffloading.config.datasource

import javax.sql.DataSource

interface CustomRoutingDataSource {
    fun isReadOnly(): Boolean
    fun toRouterDatasource(): DataSource
}