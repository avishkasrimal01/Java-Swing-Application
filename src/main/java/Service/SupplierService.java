package Service;

import Database.Database;
import Inventory.Models.Im;
import Suppliers.Models.SM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierService {

    private Database singleConn;
    public SupplierService()
    {
        singleConn=Database.getSingleInstance();
    }

    public boolean addsupplier(SM supply)
    {
        try
        {
            String query="insert into suppliers values('"+supply.id+"','"+supply.name+"','"+supply.address+"','"+supply.products+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an Employee."+ex);
            return false;
        }
    }


    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Employee details
    }


    public static void updatesupp(String Id, String newName, String address, String prod) {



        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL update statement
            String updateQuery = "UPDATE suppliers SET name=?, address=?, products=? WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, prod);
                preparedStatement.setString(4, Id);


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

    public static void deletesupp(String Id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL delete statement
            String deleteQuery = "DELETE FROM suppliers WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // Set parameter
                preparedStatement.setString(1, Id);

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
