package de.gamestart.java;

import java.sql.*;

public class Database {
    public static Connection connection;

    public static Statement statement;

    public static final String CONNECTION_URL = "jdbc:sqlite:db/database.db";

    public Database() {}

    public static void openConnect() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            statement = connection.createStatement();
        } catch(SQLException exception) {
            System.out.println("Failed to connect to SQLite Database");
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public static void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch(SQLException exception) {
            System.out.println("Failed to close SQLite Connection");
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public static ResultSet executeQuery(String sqlQuery) {
        try {
            ResultSet resultSet;
            resultSet = statement.executeQuery(sqlQuery);
            return resultSet;
        } catch(SQLException exception) {
            System.out.println("Error in executeQuery: " + exception.getMessage());
        }
        return null;
    }

    public static boolean executeUpdate(String sqlUpdate) {
        try {
            statement.executeUpdate(sqlUpdate);
            return true;
        } catch(SQLException exception) {
            System.out.println("Error in executeStatement: " + exception.getMessage());
            return false;
        }
    }
}
