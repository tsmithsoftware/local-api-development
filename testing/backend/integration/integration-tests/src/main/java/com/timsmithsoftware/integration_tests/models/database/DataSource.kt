package com.timsmithsoftware.integration_tests.models.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.SQLException
import javax.sql.DataSource

object DataSource {
    // Using Hikari for connection pooling
    private val config = HikariConfig()
    private var ds: HikariDataSource? = null

    init {
        config.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/db"
        config.username = "user"
        config.password = "password"
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        ds = HikariDataSource(config)
    }

    @get:Throws(SQLException::class)
    val source: DataSource
        get() = ds!!
}