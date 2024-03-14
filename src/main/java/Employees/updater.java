package Employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updater {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Employee details
        int employeeId = 1; // replace with the actual ID
        String newName = "New Name";
        int newAge = 30;
        String newAddress = "New Address";

        // Update employee in the database
        updateEmployee(employeeId, newName, newAge, newAddress);
    }

    public static void updateEmployee(int employeeId, String newName, int newAge, String newAddress) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL update statement
            String updateQuery = "UPDATE employee SET name=?, age=?, address=? WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, newAge);
                preparedStatement.setString(3, newAddress);
                preparedStatement.setInt(4, employeeId);

                // Execute update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    System.out.println("Employee information updated successfully.");
                } else {
                    System.out.println("No employee found with the specified ID.");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
