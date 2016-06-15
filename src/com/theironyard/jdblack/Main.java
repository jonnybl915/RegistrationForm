package com.theironyard.jdblack;

import org.h2.tools.Server;
import spark.Spark;

import java.sql.*;

public class Main {

    public static void createTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, username VARCHAR, address VARCHAR, email VARCHAR, email VARCHAR)");
    }
    public static void insertUser(Connection conn, int id, String username, String address, String email) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?, ?, ?)");
    }

    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTable(conn);

        Spark.externalStaticFileLocation("public");
        Spark.init();
    }
}
