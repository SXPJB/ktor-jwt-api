package com.fsociety.domain.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun init(env: ApplicationEnvironment) {
        Database.connect(hikari(env))
    }

    private fun hikari(
        env: ApplicationEnvironment
    ): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = env.config.property("db.url").getString()
        config.username = env.config.property("db.username").getString()
        config.password = env.config.property("db.password").getString()
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> query(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}