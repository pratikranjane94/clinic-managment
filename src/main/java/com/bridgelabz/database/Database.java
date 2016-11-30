package com.bridgelabz.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public final class Database {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/clinic_management");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}