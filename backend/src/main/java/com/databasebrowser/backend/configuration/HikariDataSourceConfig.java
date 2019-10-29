package com.databasebrowser.backend.configuration;

import com.databasebrowser.backend.model.DatabaseConnection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ConditionalOnClass(HikariDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@Configuration
public class HikariDataSourceConfig {
    private HikariConfig hikariConfig = new HikariConfig();

    public Connection getConnection(DatabaseConnection databaseConnection) throws SQLException {
        hikariConfig.setJdbcUrl("jdbc:postgresql://" + databaseConnection.getHostname() + ":" + databaseConnection.getPort() + "/" + databaseConnection.getDatabaseName());
        hikariConfig.setUsername(databaseConnection.getUsername());
        hikariConfig.setPassword(databaseConnection.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(hikariConfig).getConnection();
    }
}
