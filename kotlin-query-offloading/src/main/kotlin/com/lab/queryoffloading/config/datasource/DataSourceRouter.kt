package com.lab.queryoffloading.config.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

class DataSourceRouter(
    targetDataSources: Map<RoutingDataSourceRole, DataSource>,
    defaultTargetDataSource: DataSource
) : AbstractRoutingDataSource() {
    init {
        super.setTargetDataSources(targetDataSources.toMap())
        super.setDefaultTargetDataSource(defaultTargetDataSource)
    }

    override fun determineCurrentLookupKey(): RoutingDataSourceRole {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            RoutingDataSourceRole.READ_ONLY
        } else RoutingDataSourceRole.READ_WRITE
    }
}
