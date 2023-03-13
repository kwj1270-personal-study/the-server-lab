package com.lab.queryoffloading.config.datasource.readwrite

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.Properties
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "boardReadWriteEntityManagerFactory",
    transactionManagerRef = "boardReadWriteTransactionManger",
    basePackages = ["com.lab.queryoffloading.domain.board.persistence.rw"])
class BoardReadWriteDataSourceConfiguration {

    @Bean(name = [ENTITY_MANAGER_FACTORY])
    fun boardReadWriteEntityManagerFactory(
        @Qualifier("masterDataSource") dataSource: DataSource,
        env: Environment
    ): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.setPackagesToScan("com.lab.queryoffloading.domain.board")
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setJpaProperties(jpaProperties(env))
        return entityManagerFactoryBean
    }

    @Bean(name = [TRANSACTION_MANAGER])
    fun boardReadWriteTransactionManger(
        @Qualifier(ENTITY_MANAGER_FACTORY) entityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory.`object`!!)
    }

    companion object {
        const val TRANSACTION_MANAGER = "boardReadWriteTransactionManger"
        const val ENTITY_MANAGER_FACTORY = "boardReadWriteEntityManagerFactory"
    }
}

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "boardReadOnlyEntityManagerFactory",
    transactionManagerRef = "boardReadOnlyTransactionManger",
    basePackages = ["com.lab.queryoffloading.domain.board.persistence.ro"])
class BoardReadOnlyDataSourceConfiguration {

    @Bean(name = [ENTITY_MANAGER_FACTORY])
    fun boardReadWriteEntityManagerFactory(
        @Qualifier("slaveDataSource") dataSource: DataSource,
        env: Environment
    ): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.setPackagesToScan("com.lab.queryoffloading.domain.board")
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setJpaProperties(jpaProperties(env))
        return entityManagerFactoryBean
    }

    @Bean(name = [TRANSACTION_MANAGER])
    fun boardReadWriteTransactionManger(
        @Qualifier(ENTITY_MANAGER_FACTORY) entityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory.`object`!!)
    }

    companion object {
        const val TRANSACTION_MANAGER = "boardReadOnlyTransactionManger"
        const val ENTITY_MANAGER_FACTORY = "boardReadOnlyEntityManagerFactory"
    }
}


private fun jpaProperties(env: Environment): Properties {
    val properties = Properties()
    properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.database-platform"))
    properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"))
    properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"))
    properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"))
    return properties
}
