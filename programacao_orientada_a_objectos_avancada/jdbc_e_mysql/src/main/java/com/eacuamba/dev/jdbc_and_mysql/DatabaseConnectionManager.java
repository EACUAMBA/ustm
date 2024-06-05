package com.eacuamba.dev.jdbc_and_mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class DatabaseConnectionManager {
    private static HikariDataSource hikariDataSource;
    private static DatabaseConnectionManager instance;

    public static DatabaseConnectionManager getInstance() {
        if (isNull(instance)) {
            return instance = new DatabaseConnectionManager();
        }

        return instance;
    }

    private DatabaseConnectionManager() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("SQLiteConnectionPool");
        hikariConfig.setJdbcUrl("jdbc:sqlite:jdbc_and_mysql.db");

        try {
            DatabaseConnectionManager.hikariDataSource = new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            System.out.println("Connection pool created using Hikari");
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(printingHook);
    }

    public Connection getConnection() throws SQLException {
        return DatabaseConnectionManager.hikariDataSource.getConnection();
    }

    Thread printingHook = new Thread(() -> {
            log.info("Closing hikari!");
        if(nonNull(hikariDataSource) && !hikariDataSource.isClosed()){
            hikariDataSource.close();
        }
    });

}
