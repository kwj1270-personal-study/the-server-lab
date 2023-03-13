package com.lab.queryoffloading.config.datasource.readwrite

import org.springframework.beans.factory.annotation.Qualifier
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
    entityManagerFactoryRef = "userReadWriteEntityManagerFactory",
    transactionManagerRef = "userReadWriteTransactionManger",
    basePackages = ["com.lab.queryoffloading.domain.user.persistence.rw"])
class UserReadWriteDataSourceConfiguration {

    @Bean(name = [ENTITY_MANAGER_FACTORY])
    fun userReadWriteEntityManagerFactory(
        @Qualifier("masterDataSource") dataSource: DataSource,
        env: Environment
    ): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.setPackagesToScan("com.lab.queryoffloading.domain.user")
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setJpaProperties(jpaProperties(env))
        return entityManagerFactoryBean
    }

    @Bean(name = [TRANSACTION_MANAGER])
    fun userReadWriteTransactionManger(
        @Qualifier(ENTITY_MANAGER_FACTORY) entityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory.`object`!!)
    }

    companion object {
        const val TRANSACTION_MANAGER = "userReadWriteTransactionManger"
        const val ENTITY_MANAGER_FACTORY = "userReadWriteEntityManagerFactory"
    }
}

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "userReadOnlyEntityManagerFactory",
    transactionManagerRef = "userReadOnlyTransactionManger",
    basePackages = ["com.lab.queryoffloading.domain.user.persistence.ro"])
class UserReadOnlyDataSourceConfiguration {

    @Bean(name = [ENTITY_MANAGER_FACTORY])
    fun userReadWriteEntityManagerFactory(
        @Qualifier("slaveDataSource") dataSource: DataSource,
        env: Environment
    ): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.setPackagesToScan("com.lab.queryoffloading.domain.user")
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setJpaProperties(jpaProperties(env))
        return entityManagerFactoryBean
    }

    @Bean(name = [TRANSACTION_MANAGER])
    fun userReadWriteTransactionManger(
        @Qualifier(ENTITY_MANAGER_FACTORY) entityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory.`object`!!)
    }

    companion object {
        const val TRANSACTION_MANAGER = "userReadOnlyTransactionManger"
        const val ENTITY_MANAGER_FACTORY = "userReadOnlyEntityManagerFactory"
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
