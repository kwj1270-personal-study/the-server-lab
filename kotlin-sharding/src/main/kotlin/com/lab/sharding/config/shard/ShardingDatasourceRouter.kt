package com.lab.sharding.config.shard

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

class ShardingDatasourceRouter(
    targetDataSources: Map<String, DataSource>,
) : AbstractRoutingDataSource() {
    init {
        super.setTargetDataSources(targetDataSources.toMap())
    }

    override fun determineCurrentLookupKey(): Any {
        return if (TransactionShardingManager.isCurrentTransactionSharding()) {
            determineCurrentLookupKeyBySharding()
        } else determineCurrentLookupKey(DataSourceLookupKeyGenerator.MASTER)
    }

    private fun determineCurrentLookupKeyBySharding(): Any {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            determineCurrentLookupKey(DataSourceLookupKeyGenerator.SLAVE)
        } else determineCurrentLookupKey(DataSourceLookupKeyGenerator.MASTER)
    }

    private fun determineCurrentLookupKey(dataSourceLookUpKeyGenerator: DataSourceLookupKeyGenerator): Any =
        dataSourceLookUpKeyGenerator.determineCurrentLookupKey(
            TransactionShardingGroupManager.getCurrentTransactionShardingGroup()
        )
}
