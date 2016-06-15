package com.theironyard.jdblack;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by jonathandavidblack on 6/15/16.
 */
public class MainTest {

    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTable(conn);
        return conn;
    }
    @Test
    public void testInsertUser() throws SQLException {
        Connection conn = startConnection();
        User testUser = new User(1, "username", "address", "email");
        Main.insertUser(conn, testUser);
        ArrayList<User> list = Main.selectUsers(conn);
        conn.close();
        assertTrue(list.size() > 0);
    }
    @Test
    public void testDeleteUser() throws SQLException {
        Connection conn = startConnection();
        User testUser = new User(1, "username", "address", "email");
        User testUser2 = new User(2, "name", "address2", "email2");
        Main.insertUser(conn, testUser);
        Main.insertUser(conn, testUser2);
        Main.deleteUser(conn, 1);
        ArrayList<User> testList1 = Main.selectUsers(conn);
        conn.close();
        assertTrue(testList1.size() == 1);
    }
    @Test
    public void testUpdateUser() throws SQLException {
        Connection conn = startConnection();
        User testUser = new User(1, "testName", "testAddress", "testEmail");
        Main.insertUser(conn, testUser);
        User testUser2 = new User(1, "newName", "newAddress", "newEmail");
        Main.updateUser(conn, testUser2);

        ArrayList<User> testList = Main.selectUsers(conn);
        testUser = testList.get(0);

        conn.close();
        assertTrue(testUser.email.equals("newEmail"));
    }


}