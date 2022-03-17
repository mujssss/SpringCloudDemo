package com.mu.test;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConTest {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/springclouddemo?useSSL=false&serverTimezone=UTC";
    private static String userName = "root";
    private static String pass = "mu1996421";

    public static void main(String[] args) throws SQLException {
        System.out.println(DriverManager.getConnection(url,userName,pass));
    }
}
