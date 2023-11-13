package org.uid.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeAll;

import javax.sql.DataSource;

public class DefaultGeneratorServiceTest {

    private static DataSource dataSource;

    @BeforeAll
    public static void beforeAll()
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setConnectionTestQuery("SELECT 1");
        config.setJdbcUrl("jdbc:sqlite:../test-uid.db");
        dataSource = new HikariDataSource(config);


    }
}
