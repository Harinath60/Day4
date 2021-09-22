package com.target.training.Assignment18.programs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
@Slf4j
public class ConnectToDatabase {
    @SneakyThrows
    public static void main(String[] args) {
        String driverClassName = "org.h2.Driver";
        String url = "jdbc:h2:tcp://localhost/~/target_training_db";
        String username = "root";
        String password = "user";

        log.debug("These JDBC drivers are registered with Driver Manager");

        DriverManager.drivers().forEach(d -> log.debug("{}",d.getClass().getName()));

        Connection con = DriverManager.getConnection(url,username,password);

        log.debug("got an object of type {} for the variable con ",con.getClass().getName());

    }
}
