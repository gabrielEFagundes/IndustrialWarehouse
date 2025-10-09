package com.warehouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectate {

    private static final String URL = "jdbc:mysql://localhost:3306/WareHouse?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection begin() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
