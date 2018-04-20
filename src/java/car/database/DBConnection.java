/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tomek.buslowski
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cars";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection connection = null;
    protected static Statement stmt = null;

    private DBConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("DBConnection.getConnection: " + e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("DBConnection.closeConnection: " + e);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        /* main for tests */
        connection = DBConnection.getConnection();
        System.out.println("Connected.");
        connection.close();
        System.out.println("Closed.");
    }

}
