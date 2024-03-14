package Service;

import java.sql.*;

public class EmployeeUPDeL {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Employee details
    }


    public static void updateEmployee(String employeeId, String newName, String newAge, String newAddress,String contact) {



        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL update statement
            String updateQuery = "UPDATE employees SET name=?,address=?, age=?, contact=? WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, newAddress);
                preparedStatement.setString(3, newAge);
                preparedStatement.setString(4, contact);
                preparedStatement.setString(5, employeeId);

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

    public static void deleteEmployee(String employeeId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL delete statement
            String deleteQuery = "DELETE FROM employees WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // Set parameter
                preparedStatement.setString(1, employeeId);

                // Execute delete
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the delete was successful
                if (rowsAffected > 0) {
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("No employee found with the specified ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
