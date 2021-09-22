package com.target.training.Assignment18.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class Dbutil {
    private Dbutil(){}

    public static Connection createConnection() throws SQLException {

        ResourceBundle rb = ResourceBundle.getBundle("jdbc-info");


        String url = rb.getString("jdbc.connection.url");
        String username = rb.getString("jdbc.connection.username");
        String password = rb.getString("jdbc.connection.password");

        return DriverManager.getConnection(url,username,password);

    }
}
